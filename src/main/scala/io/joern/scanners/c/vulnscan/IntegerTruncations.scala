package io.joern.scanners.c.vulnscan

import io.shiftleft.semanticcpg.language._
import io.joern.scanners._

object IntegerTruncations {

  /**
    * Identify calls to `strlen` where return values are assigned
    * to variables of type `int`, potentially causing truncation
    * on 64 bit platforms.
    * */
  def strlenAssignmentTruncations(): Query = Query(
    title = "Truncation in assigment involving strlen call",
    description = "-",
    score = 2, { cpg =>
      cpg
        .call("strlen")
        .inAssignment
        .target
        .evalType("(g?)int")
    }
  )
}
