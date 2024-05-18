package org.maple.monitor.controller.rank

import org.maple.monitor.external.MapleExternalAPIService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime

@RestController
@RequestMapping("/update")
class RankController(
    private val mapleExternalAPIService: MapleExternalAPIService
) {

    @GetMapping
    fun update(): String {
        mapleExternalAPIService.getCharacterInfo(OffsetDateTime.now())
        return "Update"
    }
}