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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.v2.rivtabp21.GetMaternityMedicalHistoryResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryType;
import se.skltp.agp.test.producer.TestProducerDb;

@WebService(serviceName = "GetMaternityMedicalHistoryResponderService", portName = "GetMaternityMedicalHistoryResponderPort", targetNamespace = "urn:riv:clinicalprocess.healthcond.actoutcome:GetMaternityMedicalHistoryResponder:2:rivtabp21", name = "GetMaternityMedicalHistoryInteraction")
public class MaternityMedicalHistoryTestProducer implements GetMaternityMedicalHistoryResponderInterface {

    private static final Logger log = LoggerFactory.getLogger(MaternityMedicalHistoryTestProducer.class);

    private TestProducerDb testDb;

    public void setTestDb(TestProducerDb testDb) {
        this.testDb = testDb;
    }

    @Override
    @WebResult(name = "GetMaternityMedicalHistoryResponse", targetNamespace = "urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistoryResponder:2", partName = "parameters")
    @WebMethod(operationName = "GetMaternityMedicalHistory", action = "urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistoryResponder:2:GetMaternityMedicalHistory")
    public GetMaternityMedicalHistoryResponseType getMaternityMedicalHistory(
            @WebParam(partName = "LogicalAddress", name = "LogicalAddress", targetNamespace = "urn:riv:itintegration:registry:1", header = true) String logicalAddress,
            @WebParam(partName = "parameters", name = "GetMaternityMedicalHistory", targetNamespace = "urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistoryResponder:2") GetMaternityMedicalHistoryType request) {
        log.info("### Virtual service for GetMaternityMedicalHistory call the source system with logical address: {} and patientId: {}", logicalAddress, request.getPatientId().getId());

        GetMaternityMedicalHistoryResponseType response = (GetMaternityMedicalHistoryResponseType)testDb.processRequest(logicalAddress, request.getPatientId().getId());
        if (response == null) {
            // Return an empty response object instead of null if nothing is found
            response = new GetMaternityMedicalHistoryResponseType();
        }

        log.info("### Virtual service got {} documents in the reply from the source system with logical address: {} and patientId: {}", new Object[] {response.getMaternityMedicalRecord().size(), logicalAddress, request.getPatientId().getId()});

        // We are done
        return response;
    }

}
