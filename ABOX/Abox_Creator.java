package publicationOntology.abox;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;


import java.io.*;
import java.util.Random;
import java.util.*;

public class Creator {

    public static void createALL() throws IOException {

        Model r_model = ModelFactory.createDefaultModel();
        BufferedReader r_csvReader = new BufferedReader(new FileReader(Config.resource_PATH));
        String r_row;
        while ((r_row = r_csvReader.readLine()) != null) {

            String[] row_data = r_row.split(",");

            String r = row_data[0];
			
            String rUri = r;
			
			//jena.rdf function 
            Resource current_r = r_model.createResource(Config.RESOURCE_URL + rUri)
                    .addProperty(RDF.type, r_model.getResource("http://www.IdleCompute/schema/schedule-ekg/"))
                    .addProperty(r_model.createProperty(Config.PROPERTY_URL + "resource"), r)
                    .addProperty(FOAF.name, r);
        }
        r_csvReader.close();
		
		//save as nt file
        r_model.write(new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream(Config.OUTPUT_PATH + "resource.nt")), true), "NT");
        //////////////////////////////////////////

        Model t_model = ModelFactory.createDefaultModel();
        BufferedReader t_csvReader = new BufferedReader(new FileReader(Config.task_resource_PATH));
        String t_row;
        while ((t_row = t_csvReader.readLine()) != null) {

            String[] row_data = t_row.split(",");

            String t = row_data[0];
            String r = row_data[1];
			
            String tUri = t;
            String rUri = r;
			
			//jena.rdf function 
            Resource current_t = t_model.createResource(Config.RESOURCE_URL + tUri)
                    .addProperty(RDF.type, t_model.getResource("http://www.IdleCompute/schema/schedule-ekg/"))
                    .addProperty(t_model.createProperty(Config.PROPERTY_URL + "task"), t)
                    .addProperty(FOAF.name, t)
                    .addProperty(t_model.createProperty(Config.PROPERTY_URL + "assignedTo"), t_model.createResource(Config.RESOURCE_URL + rUri));;
        }
        t_csvReader.close();
		
		//save as nt file
        t_model.write(new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream(Config.OUTPUT_PATH + "task.nt")), true), "NT");
        ///////////////////////////////////////////////////
        
     

    }

}