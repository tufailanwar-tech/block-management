# PS27: Railway Maintenance Block Optimizer - Part 1 Data Layer

## Overview
This repository contains **Part 1 — Data Layer** for **PS27: Railway Maintenance Block Optimizer**. The solution is built with **Java Spring Boot**, **Supabase PostgreSQL**, **Supabase Auth foundation**, **Supabase Storage integration**, **CSV & JSON Import Engine**, **Data Validation & Normalization**, **Integration Adapters**, and pre-seeded **Synthetic Railway Data**.

---

## 1. Project Architecture

```
                      +------------------------------------------+
                      |         External Systems / Files         |
                      |   TMS  |  SMMS  |  TDMS  |  COA  | CSV/JSON |
                      +--------------------+---------------------+
                                           |
                                           v
                      +------------------------------------------+
                      |        Integration & Import Layer        |
                      |  - CSV / JSON Ingestion Engine           |
                      |  - Integration Adapters (TMS/SMMS/etc)   |
                      |  - Validation & Normalization Pipeline   |
                      +--------------------+---------------------+
                                           |
                                           v
+--------------------+---------------------------------------------------+--------------------+
| Supabase Auth      |             Spring Boot REST Controller Layer     | Supabase Storage   |
| (JWT Security)     |  - AuthController     - MaintenanceController     | (Import File Store)|
|                    |  - BlockRequestController  - TrainController      |                    |
|                    |  - CorridorController - ImportController          |                    |
|                    |  - AnalyticsController - IntegrationController    |                    |
+---------+----------+---------------------+-----------------------------+---------+----------+
          |                                |                                       |
          +--------------------------------+---------------------------------------+
                                           v
                      +------------------------------------------+
                      |           Service & Repository Layer     |
                      |  - Validation & Conflict Rule Checker    |
                      |  - Data Normalization & Data Seeder      |
                      |  - JPA Repositories & Entities           |
                      +--------------------+---------------------+
                                           |
                                           v
                      +------------------------------------------+
                      |          Supabase PostgreSQL DB          |
                      |  - Core Schema (Tables, FKs, Indexes)    |
                      +------------------------------------------+
```

---

## 2. Database Schema (Supabase PostgreSQL)

The database schema consists of 16 relational tables designed for railway corridor asset tracking, train schedules, maintenance requests, conflicts, and audit tracking:

- `departments`: Railway departments (TRD, S&T, Engineering, Operations).
- `users`: User profiles with roles (ADMIN, DEPARTMENT_USER, PLANNER, APPROVER).
- `corridors`: Railway line corridors (e.g. NDLS-CNB, BCT-ADI) with length, track count, and speed ratings.
- `assets`: Physical railway assets (OHE catenary, Track Segments, Signals, Switches, Bridges).
- `maintenance_tasks`: Maintenance requirements with priorities, durations, block types, and frequencies.
- `trains`: Passenger and freight trains with priority levels.
- `train_schedules`: Detailed spatial and temporal train movement schedules.
- `block_requests`: Department requests for maintenance blocks (start/end time, spatial KM bounds, status).
- `block_plans`: Weekly and monthly master maintenance plans.
- `block_plan_tasks`: Scheduled tasks associated with approved master plans.
- `constraints`: Corridor capacity and operational restriction rules.
- `conflicts`: Detected train-block and block-block spatial/temporal overlaps.
- `optimization_runs`: Run history for plan generator (Data stub).
- `optimization_results`: Optimization output metrics (Data stub).
- `audit_logs`: Audit trail of all critical operations.
- `integration_sync_logs`: External system synchronization logs (TMS, SMMS, TDMS, COA).

---

## 3. Supabase Configuration

### PostgreSQL Connection
The Spring Boot application connects directly to Supabase PostgreSQL using JDBC SSL mode.
Update `src/main/resources/application.yml` or set environment variables:
- `SUPABASE_DB_HOST`: `<your-project-id>.pooler.supabase.com`
- `SUPABASE_DB_NAME`: `postgres`
- `SUPABASE_DB_USER`: `postgres.<your-project-id>`
- `SUPABASE_DB_PASSWORD`: `<your-supabase-db-password>`

### Supabase Storage
For uploading CSV and JSON files to Supabase Storage buckets, configure:
- `SUPABASE_URL`: `https://<your-project-id>.supabase.co`
- `SUPABASE_KEY`: `<service-role-key>`
- `SUPABASE_STORAGE_BUCKET`: `railway-imports`

---

## 4. How to Run the Backend

### Prerequisites
- Java 21+ JDK

### Building & Running
Run with local profile (embedded H2 PostgreSQL mode with auto-seeded synthetic data):
```bash
./maven_tool/apache-maven-3.9.6/bin/mvn spring-boot:run
```

To run with Supabase profile connected to live Supabase cloud PostgreSQL:
```bash
./maven_tool/apache-maven-3.9.6/bin/mvn spring-boot:run -Dspring-boot.run.profiles=supabase
```

The application starts on port `8080`.

---

## 5. API Endpoints Reference

### Authentication
- `POST /api/auth/login`: Login user and receive Bearer JWT token.
- `GET /api/auth/me?userId={id}`: Get user profile.

### Corridors & Assets
- `GET /api/corridors`: List all railway corridors.
- `POST /api/corridors`: Create a new corridor.
- `GET /api/corridors/{id}/availability`: Get corridor capacity and availability status.
- `GET /api/assets`: List physical assets (filter by `corridorId`).
- `POST /api/assets`: Add asset.

### Maintenance Tasks & Block Requests
- `GET /api/maintenance`: List all maintenance tasks.
- `POST /api/maintenance`: Create maintenance task.
- `GET /api/block-requests`: List all block requests.
- `POST /api/block-requests`: Create a new block request (triggers conflict detection).
- `PUT /api/block-requests/{id}/status`: Update block request status.

### Trains & Schedules
- `GET /api/trains`: List trains.
- `POST /api/trains`: Create train.
- `GET /api/train-schedules`: List train schedules.
- `POST /api/train-schedules`: Create train schedule.

### Data Import & Storage
- `POST /api/import/csv?type={corridors|assets|trains|schedules|tasks|block-requests}`: Import data from CSV file.
- `POST /api/import/json?type={corridors|trains|tasks}`: Import data from JSON payload.
- `POST /api/import/supabase-storage`: Upload raw import file to Supabase Storage bucket.

### Conflicts & Analytics
- `GET /api/conflicts`: List detected train-block and block-block conflicts.
- `GET /api/analytics/summary`: Aggregate analytics dashboard summary.
- `GET /api/analytics/before-after`: Before vs after optimization comparison metrics.

### External System Integration
- `POST /api/integrations/sync/{TMS|SMMS|TDMS|COA}`: Trigger external system integration sync.
