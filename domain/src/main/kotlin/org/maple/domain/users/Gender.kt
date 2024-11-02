package org.maple.domain.users

enum class Gender {
    MALE,
    FEMALE;

    companion object {
        fun find(str: String) = entries.find { it.name == str }
    }
}