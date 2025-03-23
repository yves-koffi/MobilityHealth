package fr.yjk.mobility.health.utils.extensions


fun Map<String, List<String>>.error(key: String): String? = this[key]?.first()
