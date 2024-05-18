package org.maple.monitor.external

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.maple.monitor.service.common.DateFormats
import java.time.OffsetDateTime

class MapleExternalAPIServiceTest() : DescribeSpec(
    {
        describe("MapleExternalAPIServiceTest") {
            it("DateFormats") {
                val formattedDate = DateFormats.refine(OffsetDateTime.now())
                formattedDate shouldNotBe null
//                logger().trace(formattedDate)
            }
        }
    }
)