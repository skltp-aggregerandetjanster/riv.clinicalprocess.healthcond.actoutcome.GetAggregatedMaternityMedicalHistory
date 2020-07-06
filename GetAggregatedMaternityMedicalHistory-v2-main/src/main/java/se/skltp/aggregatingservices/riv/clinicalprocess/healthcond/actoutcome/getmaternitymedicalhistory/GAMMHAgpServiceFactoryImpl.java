package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryType;
import se.skltp.aggregatingservices.AgServiceFactoryBase;

@Log4j2
public class GAMMHAgpServiceFactoryImpl
    extends AgServiceFactoryBase<
        GetMaternityMedicalHistoryType, GetMaternityMedicalHistoryResponseType> {

  @Override
  public String getPatientId(GetMaternityMedicalHistoryType queryObject) {
    return queryObject.getPatientId().getId();
  }

  @Override
  public String getSourceSystemHsaId(GetMaternityMedicalHistoryType queryObject) {
    return queryObject.getSourceSystemHSAId();
  }

  @Override
  public GetMaternityMedicalHistoryResponseType aggregateResponse(
      List<GetMaternityMedicalHistoryResponseType> aggregatedResponseList) {
    GetMaternityMedicalHistoryResponseType aggregatedResponse =
        new GetMaternityMedicalHistoryResponseType();
    for (GetMaternityMedicalHistoryResponseType response : aggregatedResponseList) {
      aggregatedResponse.getMaternityMedicalRecord().addAll(response.getMaternityMedicalRecord());
    }

    return aggregatedResponse;
  }
}
