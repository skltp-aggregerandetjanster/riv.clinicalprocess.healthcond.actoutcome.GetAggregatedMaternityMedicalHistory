package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedmaternitymedicalcondition

trait CommonParameters {
  val serviceName:String     = "MaternityMedicalHistory"
  val urn:String             = "urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistoryResponder:2"
  val responseElement:String = "GetMaternityMedicalHistoryResponse"
  val responseItem:String    = "maternityMedicalRecord"
  var baseUrl:String         = if (System.getProperty("baseUrl") != null && !System.getProperty("baseUrl").isEmpty()) {
                                   System.getProperty("baseUrl")
                               } else {
                                   "http://33.33.33.33:8081/GetAggregatedMaternityMedicalHistory/service/v2"
                               }
}
