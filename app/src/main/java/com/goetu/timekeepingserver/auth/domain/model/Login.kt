package com.goetu.go3timekeepingserver.timelog.remote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val attendance_type: String? = null,
    val branch_device_id: String? = null,
    val cn_time: String? = null,
    val created_at: String? = null,
    val designation_name: String? = null,
    val employee_rc: String? = null,
    val end_shift: String? = null,
    val event_rc: String? = null,
    val first_name: String? = null,
    val gender_code: String? = null,
    val id: Int? = null,
    val image_path: String? = null,
    val image_url: String? = null,
    val is_breaklog_hol: Int? = null,
    val is_half_day: Int? = null,
    val is_office: Int? = null,
    val is_rest_day: Int? = null,
    val is_wfh: Int? = null,
    val last_name: String? = null,
    val middle_name: String? = null,
    val notes: String? = null,
    val ph_time: String? = null,
    val reference_code: String? = null,
    val request_id: Int? = null,
    val start_shift: String? = null,
    val time: String? = null,
    val time_in_time_out: String? = null,
    val time_source_rc: String? = null,
    val title: String? = null,
    val updated_at: String? = null,
    val us_time: String? = null,
    val with_break: Int
)
