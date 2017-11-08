/**
 * Copyright (c) 2014 Inera AB, <http://inera.se/>
 *
 * This file is part of SKLTP.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.integrationtest;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import riv.clinicalprocess.healthcond.actoutcome.v2.CVType;
import riv.clinicalprocess.healthcond.actoutcome.v2.HealthcareProfessionalType;
import riv.clinicalprocess.healthcond.actoutcome.v2.LegalAuthenticatorType;
import riv.clinicalprocess.healthcond.actoutcome.v2.MaternityMedicalRecordBodyType;
import riv.clinicalprocess.healthcond.actoutcome.v2.MaternityMedicalRecordType;
import riv.clinicalprocess.healthcond.actoutcome.v2.OrgUnitType;
import riv.clinicalprocess.healthcond.actoutcome.v2.PQType;
import riv.clinicalprocess.healthcond.actoutcome.v2.PatientSummaryHeaderType;
import riv.clinicalprocess.healthcond.actoutcome.v2.PersonIdType;
import riv.clinicalprocess.healthcond.actoutcome.v2.PregnancyCheckupRecordType;
import riv.clinicalprocess.healthcond.actoutcome.v2.RegistrationRecordType;
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
        response.setMaternityMedicalRecordHeader(generatePatientSummaryHeader(logicalAddress, businessObjectId, registeredResidentId, time));
        response.setMaternityMedicalRecordBody(generateRecordBody());
        return response;
    }


    protected PQType buildPQ(String unit, double value) {
		PQType pq = new PQType();
		pq.setUnit(unit);
		pq.setValue(value);
		return pq;
    }

    protected MaternityMedicalRecordBodyType generateRecordBody() {
    	MaternityMedicalRecordBodyType body = new MaternityMedicalRecordBodyType();
    	PregnancyCheckupRecordType pregnancyCheckUpRecord = new PregnancyCheckupRecordType();
    	{
    		pregnancyCheckUpRecord.setProteinuria(buildPQ("mg/dL", 30.0));
    		pregnancyCheckUpRecord.setBloodPressureDiastolic(buildPQ("mm Hg", 83.7));
    		pregnancyCheckUpRecord.setBloodPressureSystolic(buildPQ("mm Hg", 135.2));
    		pregnancyCheckUpRecord.setCompleteWeeksOfGestation(22);
    		pregnancyCheckUpRecord.getFetalHeartRate().add(buildPQ("bpm", 82));
    		pregnancyCheckUpRecord.getFetalPosition().add(new BigInteger("1"));
    		pregnancyCheckUpRecord.getFetalPresentation().add(new BigInteger("1"));
    		pregnancyCheckUpRecord.setGlycosuria(buildPQ("mg/dL", 250.0));
    		pregnancyCheckUpRecord.setHaemoglobin(buildPQ("g/dL", 14.3));
    		pregnancyCheckUpRecord.setSymphysisFundalHeight(buildPQ("cm", 20.0));
    		pregnancyCheckUpRecord.getTypeOfLeave().add(new BigInteger("0"));
    		pregnancyCheckUpRecord.setWeight(buildPQ("g", 301.0));
    	}
    	body.setPregnancyCheckupRecord(pregnancyCheckUpRecord);
    	body.setPostDeliveryRecord(null);
    	RegistrationRecordType registrationRecord = new RegistrationRecordType();
    	{
	    	registrationRecord.setAssessmentAtFirstContactStandardCare(true);
	    	registrationRecord.setContraceptiveDiscontinued("20130401");
	    	registrationRecord.setDiseasesDiabetesMellitus(true);
	    	registrationRecord.setDiseasesEndocineDiseases(false);
	    	registrationRecord.setDiseasesRecurrentUrinaryTractInfections(false);
	    	registrationRecord.setDiseasesThrombosis(false);
	    	registrationRecord.setExpectedDayOfDeliveryFromEmbryonicTransfer(null);
	    	registrationRecord.setExpectedDayOfDeliveryFromLastMenstrualPeriod("20130208");
	    	registrationRecord.setExpectedDayOfDeliveryFromUltrasoundScan(null);
	    	registrationRecord.setIndicationPregnancy("20130610");
	    	registrationRecord.setInfertility(new BigDecimal(0.0));
	    	registrationRecord.setLastMenstrualPeriod("20130503");
	    	registrationRecord.setWeight(buildPQ("kg", 69));
    	}
    	body.setRegistrationRecord(registrationRecord);
    	return body;
	}

	protected PatientSummaryHeaderType generatePatientSummaryHeader(String logicalAddress, String businessObjectId, String registeredResidentId, String time) {
    	PatientSummaryHeaderType header = new PatientSummaryHeaderType();
    	{
	    	header.setApprovedForPatient(true);
	    	HealthcareProfessionalType author = new HealthcareProfessionalType();
	    	{
	    		author.setAuthorTime("20130707010203");
	    		author.setHealthcareProfessionalCareGiverHSAId("HSA-SLL-01");
	    		author.setHealthcareProfessionalCareUnitHSAId(logicalAddress + "-careUnit");
	    		author.setHealthcareProfessionalHSAId("HSA-BMOR-1");
	    		author.setHealthcareProfessionalName("Ulla Bandage");
	    		OrgUnitType orgUnit = new OrgUnitType();
	    		orgUnit.setOrgUnitAddress("Bättringsvägen 1");
	    		orgUnit.setOrgUnitHSAId(logicalAddress + "-orgUnit");
		        if(TestProducerDb.TEST_LOGICAL_ADDRESS_1.equals(logicalAddress)){
		            orgUnit.setOrgUnitName("Vårdcentralen Kusten, Kärna");
		        } else if(TestProducerDb.TEST_LOGICAL_ADDRESS_2.equals(logicalAddress)){
		        	orgUnit.setOrgUnitName("Vårdcentralen Molnet");
		        } else {
		        	orgUnit.setOrgUnitName("Vårdcentralen Stacken");
		        }
	    		author.setHealthcareProfessionalOrgUnit(orgUnit);
	    		CVType roleCode = new CVType();
	    		roleCode.setCode("205011");
	    		roleCode.setCodeSystem("OID 1.2.752.129.2.2.1.4");
	    		roleCode.setCodeSystemName("KV Befattning");
	    		roleCode.setCodeSystemVersion("3.2");
	    		roleCode.setDisplayName("Barnmorska, mottagning/rådgivning");
	    		roleCode.setOriginalText("Barnmorska, mottagning/rådgivning");
	    		author.setHealthcareProfessionalRoleCode(roleCode);
	    	}
	    	header.setAccountableHealthcareProfessional(author);
	    	header.setDocumentTime("20130707010203");

	        header.setCareContactId(businessObjectId);
	    	header.setDocumentId("MATER-" + (int)(Math.random()*10000));
	    	header.setDocumentTime("20130707010203");
	    	LegalAuthenticatorType legalAuthenticator = new LegalAuthenticatorType();
	    	{
		    	legalAuthenticator.setLegalAuthenticatorHSAId("HSA-BMOR-1");
		    	legalAuthenticator.setSignatureTime("20130707112233");
	    	}
	    	header.setLegalAuthenticator(legalAuthenticator);
	    	PersonIdType patientId = new PersonIdType();
	    	{
		    	patientId.setId(registeredResidentId);
				patientId.setType("1.2.752.129.2.1.3.1");
	    	}
			header.setPatientId(patientId);

	    	header.setSourceSystemHSAId(logicalAddress);

    	}
    	return header;
    }


}
