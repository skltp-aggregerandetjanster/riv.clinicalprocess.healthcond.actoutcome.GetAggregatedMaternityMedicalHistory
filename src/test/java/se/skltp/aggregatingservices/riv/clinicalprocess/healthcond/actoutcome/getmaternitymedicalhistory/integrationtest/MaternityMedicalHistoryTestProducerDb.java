package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.integrationtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.AntenatalFollowUpRecordType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.AuthorType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.MaternityMedicalRecordBodyType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.MaternityMedicalRecordType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.PatientIdType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.PatientSummaryHeaderType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.PostDeliveryRecordType;
import se.riv.clinicalprocess.healthcond.actoutcome.v2.RegistrationRecordType;
import se.skltp.agp.test.producer.TestProducerDb;

public class MaternityMedicalHistoryTestProducerDb extends TestProducerDb {

    private static final Logger log = LoggerFactory.getLogger(MaternityMedicalHistoryTestProducerDb.class);

    @Override
    public Object createResponse(Object... responseItems) {
        log.debug("Creates a response with {} items", responseItems);
        GetMaternityMedicalHistoryResponseType response = new GetMaternityMedicalHistoryResponseType();
        for (int i = 0; i < responseItems.length; i++) {
            response.getMaternityMedicalRecord().add((MaternityMedicalRecordType)responseItems[i]);
        }
        return response;
    }

    public static final String TEST_REASON_DEFAULT = "default reason";
    public static final String TEST_REASON_UPDATED = "updated reason";

    @Override
    public Object createResponseItem(String logicalAddress, String registeredResidentId, String businessObjectId, String time) {

        log.debug("Created one response item for logical-address {}, registeredResidentId {} and businessObjectId {}",
                new Object[] {logicalAddress, registeredResidentId, businessObjectId});

        MaternityMedicalRecordType response = new MaternityMedicalRecordType();
        PatientSummaryHeaderType header = new PatientSummaryHeaderType();
        PatientIdType patientId = new PatientIdType();
        patientId.setId(registeredResidentId);
        patientId.setType("1.2.752.129.2.1.3.1");
        header.setPatientId(patientId);
        header.setApprovedForPatient(true);
        header.setSourceSystemHSAid(logicalAddress);
        header.setCareContactId(businessObjectId);
        header.setDocumentTime(time);
        
        // TODO: Set stuff

        MaternityMedicalRecordBodyType body = new MaternityMedicalRecordBodyType();
        AntenatalFollowUpRecordType followUp = new AntenatalFollowUpRecordType();
        PostDeliveryRecordType postDelivery = new PostDeliveryRecordType();
        RegistrationRecordType regRecord = new RegistrationRecordType();
        
        // TODO: Set stuff
        
        body.setAntenatalFollowUpRecord(followUp);
        body.setPostDeliveryRecord(postDelivery);
        body.setRegistrationRecord(regRecord);
        
        AuthorType author = new AuthorType();
        author.setCareUnitHSAid(logicalAddress); // TODO ???
        if(TestProducerDb.TEST_LOGICAL_ADDRESS_1.equals(logicalAddress)){
            author.setAuthorOrgUnitName("Vårdcentralen Kusten, Kärna"); // TODO ???
        } else if(TestProducerDb.TEST_LOGICAL_ADDRESS_2.equals(logicalAddress)){
            author.setAuthorOrgUnitName("Vårdcentralen Molnet");
        } else {
            author.setAuthorOrgUnitName("Vårdcentralen Stacken");
        }
        header.setAuthor(author);
        response.setMaternityMedicalRecordHeader(header);
        response.setMaternityMedicalRecordBody(body);
        return response;
    }
}
