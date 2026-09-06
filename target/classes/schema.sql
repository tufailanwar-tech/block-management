-- PS27: Railway Maintenance Block Optimizer - Database Schema for Supabase PostgreSQL
-- Database DDL Script

CREATE TABLE IF NOT EXISTS departments (
    id UUID PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255),
    full_name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- ADMIN, DEPARTMENT_USER, PLANNER, APPROVER
    department_id UUID REFERENCES departments(id) ON DELETE SET NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS corridors (
    id UUID PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    source_station VARCHAR(100) NOT NULL,
    destination_station VARCHAR(100) NOT NULL,
    length_km DOUBLE PRECISION NOT NULL,
    max_speed_kmh INT NOT NULL,
    number_of_tracks VARCHAR(50) NOT NULL, -- SINGLE, DOUBLE, MULTIPLE
    status VARCHAR(50) NOT NULL, -- ACTIVE, MAINTENANCE_RESTRICTED, INACTIVE
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS assets (
    id UUID PRIMARY KEY,
    corridor_id UUID NOT NULL REFERENCES corridors(id) ON DELETE CASCADE,
    asset_code VARCHAR(50) UNIQUE NOT NULL,
    asset_type VARCHAR(50) NOT NULL, -- OHE, TRACK_SEGMENT, SIGNAL, SWITCH, BRIDGE
    location_km_start DOUBLE PRECISION NOT NULL,
    location_km_end DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL, -- OPERATIONAL, DEGRADED, UNDER_MAINTENANCE
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS maintenance_tasks (
    id UUID PRIMARY KEY,
    department_id UUID NOT NULL REFERENCES departments(id),
    asset_id UUID REFERENCES assets(id),
    task_type VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority VARCHAR(50) NOT NULL, -- CRITICAL, HIGH, MEDIUM, LOW
    estimated_duration_minutes INT NOT NULL,
    required_block_type VARCHAR(50) NOT NULL, -- TRAFFIC_BLOCK, POWER_BLOCK, INTEGRATED_BLOCK
    periodic_frequency VARCHAR(50) NOT NULL, -- DAILY, WEEKLY, MONTHLY, AD_HOC
    status VARCHAR(50) NOT NULL, -- DRAFT, SUBMITTED, APPROVED, CANCELLED
    source_system VARCHAR(50) NOT NULL, -- MANUAL, SMMS, TMS
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS trains (
    id UUID PRIMARY KEY,
    train_number VARCHAR(50) UNIQUE NOT NULL,
    train_name VARCHAR(255) NOT NULL,
    train_type VARCHAR(50) NOT NULL, -- PASSENGER_EXPRESS, FREIGHT, SUBURBAN, MEMU
    priority_level INT NOT NULL, -- 1 to 5
    origin_station VARCHAR(100) NOT NULL,
    destination_station VARCHAR(100) NOT NULL,
    source_system VARCHAR(50) NOT NULL, -- MANUAL, COA, TDMS
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS train_schedules (
    id UUID PRIMARY KEY,
    train_id UUID NOT NULL REFERENCES trains(id) ON DELETE CASCADE,
    corridor_id UUID NOT NULL REFERENCES corridors(id) ON DELETE CASCADE,
    arrival_time TIMESTAMP WITH TIME ZONE NOT NULL,
    departure_time TIMESTAMP WITH TIME ZONE NOT NULL,
    start_km DOUBLE PRECISION NOT NULL,
    end_km DOUBLE PRECISION NOT NULL,
    day_of_week VARCHAR(20) NOT NULL,
    frequency_pattern VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS block_requests (
    id UUID PRIMARY KEY,
    request_number VARCHAR(50) UNIQUE NOT NULL,
    maintenance_task_id UUID NOT NULL REFERENCES maintenance_tasks(id),
    corridor_id UUID NOT NULL REFERENCES corridors(id),
    asset_id UUID REFERENCES assets(id),
    department_id UUID NOT NULL REFERENCES departments(id),
    requested_by_user_id UUID REFERENCES users(id),
    block_type VARCHAR(50) NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    duration_minutes INT NOT NULL,
    start_km DOUBLE PRECISION NOT NULL,
    end_km DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL, -- PENDING, VALIDATED, CONFLICT_DETECTED, APPROVED, REJECTED
    remarks TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS block_plans (
    id UUID PRIMARY KEY,
    plan_name VARCHAR(255) NOT NULL,
    horizon_type VARCHAR(50) NOT NULL, -- WEEKLY, MONTHLY
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL, -- DRAFT, IN_REVIEW, APPROVED, REJECTED
    created_by_user_id UUID REFERENCES users(id),
    approved_by_user_id UUID REFERENCES users(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS block_plan_tasks (
    id UUID PRIMARY KEY,
    block_plan_id UUID NOT NULL REFERENCES block_plans(id) ON DELETE CASCADE,
    block_request_id UUID NOT NULL REFERENCES block_requests(id),
    scheduled_start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    scheduled_end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    assigned_priority VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS constraints (
    id UUID PRIMARY KEY,
    constraint_type VARCHAR(100) NOT NULL,
    description TEXT,
    parameters JSONB,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS conflicts (
    id UUID PRIMARY KEY,
    block_request_id UUID REFERENCES block_requests(id) ON DELETE CASCADE,
    conflicting_train_schedule_id UUID REFERENCES train_schedules(id) ON DELETE CASCADE,
    conflicting_block_request_id UUID REFERENCES block_requests(id) ON DELETE CASCADE,
    conflict_type VARCHAR(100) NOT NULL, -- TRAIN_BLOCK_OVERLAP, ASSET_DOUBLE_BOOKING, CAPACITY_EXCEEDED
    severity VARCHAR(50) NOT NULL, -- HIGH, MEDIUM, LOW
    details TEXT,
    status VARCHAR(50) NOT NULL, -- UNRESOLVED, RESOLVED, IGNORED
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS optimization_runs (
    id UUID PRIMARY KEY,
    run_number VARCHAR(50) UNIQUE NOT NULL,
    block_plan_id UUID REFERENCES block_plans(id),
    status VARCHAR(50) NOT NULL, -- PENDING, RUNNING, COMPLETED, FAILED
    parameters TEXT,
    started_at TIMESTAMP WITH TIME ZONE,
    completed_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS optimization_results (
    id UUID PRIMARY KEY,
    optimization_run_id UUID NOT NULL REFERENCES optimization_runs(id) ON DELETE CASCADE,
    total_blocks_scheduled INT NOT NULL,
    conflicts_resolved INT NOT NULL,
    score DOUBLE PRECISION NOT NULL,
    metrics TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS audit_logs (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    action VARCHAR(100) NOT NULL,
    entity_name VARCHAR(100) NOT NULL,
    entity_id UUID,
    old_values TEXT,
    new_values TEXT,
    ip_address VARCHAR(50),
    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS integration_sync_logs (
    id UUID PRIMARY KEY,
    system_name VARCHAR(50) NOT NULL, -- TMS, SMMS, TDMS, COA
    sync_type VARCHAR(50) NOT NULL, -- MANUAL, SCHEDULED, EVENT
    records_processed INT NOT NULL,
    status VARCHAR(50) NOT NULL, -- SUCCESS, FAILED, PARTIAL
    error_details TEXT,
    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
