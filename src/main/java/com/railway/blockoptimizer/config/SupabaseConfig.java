package com.railway.blockoptimizer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupabaseConfig {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;

    @Value("${supabase.anon-key}")
    private String anonKey;

    @Value("${supabase.storage.bucket}")
    private String storageBucket;

    public String getSupabaseUrl() { return supabaseUrl; }
    public String getServiceRoleKey() { return serviceRoleKey; }
    public String getAnonKey() { return anonKey; }
    public String getStorageBucket() { return storageBucket; }
}
