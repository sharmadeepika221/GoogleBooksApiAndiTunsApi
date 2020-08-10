package com.IDEXX.animana.assessment.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {
    private SearchDataFetcher searchDataFetcher;

    @Value("classpath:search.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    public GraphQLService(SearchDataFetcher searchDataFetcher) {
        this.searchDataFetcher = searchDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        //log.info("Entering graphql loadschema");
        File file = resource.getFile();

        // parse schema file
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("searchByString", searchDataFetcher))
                .build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }
 }
