/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package me.joaoemilio.petclinic.microservice.askthevet.resource;

import me.joaoemilio.petclinic.microservice.askthevet.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import me.joaoemilio.petclinic.microservice.askthevet.dao.AskTheVetRepository;

import java.util.List;

/**
 * Annotated Resource class.
 */

@Component
@Path("/askthevet")
public class AskTheVetResource {

    @Autowired
    private AskTheVetRepository repository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addQuestion(Question model) {
        System.out.println("model: " + model );
        repository.add(model);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Question> list = repository.findAll();
        return Response.status(Response.Status.ACCEPTED).entity(list).build();
    }
}
