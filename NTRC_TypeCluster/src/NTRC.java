import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.system.PrefixMap;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;



public class NTRC {


	
			
    public void queryEndpoint(String szQuery, String szEndpoint)
    throws Exception
    {
        // Create a Query with the given String
        Query query = QueryFactory.create(szQuery);

        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(
                szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP)qexec).addParam("timeout", "10000");


        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");

            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();
                
                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
    } // End of Method: queryEndpoint()


    public static void main(String[] args) throws IOException {
    	
    	//PREFIX: rdf <http://dbpedia.org/resource/>;
    	//rdf ='http://www.w3.org/1999/02/22-rdf-syntax-ns#'
        // =================SPARQL Query here for search knowledge online========================
    	String syntax_rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    	String syntax_resource = "http://dbpedia.org/resource/";
    	String syntax_ontology = "http://dbpedia.org/ontology/";
    	/*
    	//http://dbpedia.org/resource/Bill_Clinton
    	//http://www.w3.org/1999/02/22-rdf-syntax-ns#type      syntax=http://www.w3.org/1999/02/22-rdf-syntax-ns# +type
    	//http://www.w3.org/1999/02/22-rdf-syntax-ns#Property  syntax=http://www.w3.org/1999/02/22-rdf-syntax-ns# +Property
    	
    	//String o = "http://dbpedia.org/ontology/starring";
    		
    	String Str = new String("http://dbpedia.org/ontology/starring");
    	System.out.println(Str.endsWith("starring"));
    	//PREFIX dbpo: <http://dbpedia.org/ontology/> ;
    	
    	//prefix dbpo: <http://dbpedia.org/ontology/>
    	//String httpQuery= "http://dbpedia.org/ontology/starring";
    	//String szQuery = "select * where {?httpQuery ?Predicate ?Object} LIMIT 10";
    //   String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Subject = dbpo:starring)}";
  //  String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Subject = < + syntax_ontology + starring> && ?Predicate = <http://www.w3.org/2000/01/rdf-schema#range>)}";
    	// String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Subject = o && ?Predicate = <http://www.w3.org/2000/01/rdf-schema#range>)}";
    //String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Subject = <http://dbpedia.org/ontology/starring>)}";
        // String szQuery = "select * where {?Subject ?Predicate ?Object} LIMIT 1";
         * 
      
    
    	  
    	Scanner user_input = new Scanner( System.in );
		System.out.println("Input your first instance for type prediction in DBPedia Knowledge Graph");
		String input;
		input=  user_input.next();
		//System.out.println("Input your first instance for type prediction in DBPedia Knowledge Graph" + input);
		//syntax_Query = syntax_resource + input;
		//System.out.println("New Line is : " + syntax_Query);
    	 */	
		
    	//String x="http://dbpedia.org/resource/Bill_Clinton";
    	//String x="Bill_Clinton";
		 // =================SPARQL Query Final========================
		//String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Object = <syntax_Query>)}";
    	String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Predicate = <http://dbpedia.org/ontology/starring>)}";
		//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/>" + "select * "+ "where {?Subject " + "?Predicate" + "?Object" + " filter(?Object = rs:'"+ x +"' )" + "}";
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where {?Subject ?Predicate ?Object filter(?Object = rs:'" + "'Bill_Clinton'" + "' )" + "}";
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}";
     	//String szQuery =  "select * where {?Subject ?Predicate ?Object filter(?Subject = <http://dbpedia.org/ontology/starring> && ?Predicate = <http://www.w3.org/2000/01/rdf-schema#range>)}";
    	
    	
    	// =============================Step One===================================
    	// ----------------------------Select All Incoming & Outgoing Relations of this Instance---------------------------------
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where{ {?Subject ?Predicate ?Object filter(?Subject = rs:Bill_Clinton)} UNION {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}}";
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select distinct ?Predicate where{ {?Subject ?Predicate ?Object filter(?Subject = rs:Bill_Clinton)} UNION {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}}";
    	
    	// =============================Step Two===================================
    	// ----------------------------Select All Incoming Relations of this Instance---------------------------------
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where {?Subject ?Predicate ?Object filter(?Object = rs:Arnold_Schwarzenegger)}";
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where {?Subject ?Predicate ?Object filter(?Subject = rs:Arnold_Schwarzenegger)}";
    	
    	//-----------------------------------Incoming Relations-------------------------------
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}";
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select distinct ?Predicate where {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}";
    	//-----------------------------------Outgoing relations-------------------------------
   // String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select * where {?Subject ?Predicate ?Object filter(?Subject = rs:Bill_Clinton)}";
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select distinct ?Predicate where {?Subject ?Predicate ?Object filter(?Subject = rs:Bill_Clinton)}";
    	
    	
    	
    	// =============================Step Three===================================
    	// -----------------Select all Distinct Relations of this Instance---------------------------------
    	//String szQuery = "PREFIX rs: <http://dbpedia.org/resource/> " + "select distinct ?Predicate where {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}";
    	

    	// =============================Step Four===================================
    	// ----------------------------Validate Property Type Relations of these incoming relations for this Instance---------------------------------
    	//String szQuery= "PREFIX ST: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX SR: <http://dbpedia.org/ontology/>  " + "select * where {?Subject ?Predicate ?Object filter(?Object = ST:Property && ?Predicate = ST:type)}";
    	
    	//String szQuery2= "PREFIX ST: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX SR: <http://dbpedia.org/ontology/>  " + "select distinct ?Subject where {?Subject ?Predicate ?Object filter(?Object = ST:Property && ?Predicate = ST:type)}";
    	//Srting szQuery = "select * where {"+ szQuery3 + "UNION" + szQuery2+ "}";
    	
    	//String szQuery= "PREFIX rs: <http://dbpedia.org/resource/> " + "PREFIX ST: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX SR: <http://dbpedia.org/ontology/>  " + "select distinct ?Subject where {{?Subject ?Predicate ?Object filter(?Object = ST:Property && ?Predicate = ST:type)} FILTER NOT EXISTS {select distinct ?Predicate where {?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)}} }";
   // 	String szQuery= "PREFIX rs: <http://dbpedia.org/resource/> " + "PREFIX ST: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX SR: <http://dbpedia.org/ontology/>  " + "select distinct ?Predicate where {{?Subject ?Predicate ?Object filter(?Object = rs:Bill_Clinton)} minus {select distinct ?Subject where {?Subject ?Predicate ?Object filter(?Object = ST:Property && ?Predicate = ST:type) } } }";
    	// =============================Step Four +===================================
    	// ----------------------------Validate Property Type Relations of these incoming relations for this Instance---------------------------------
    //	String szQuery= "PREFIX ST: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX SR: <http://dbpedia.org/ontology/>  " + "select * where {?Subject ?Predicate ?Object filter(?Subject = SR:starring && ?Predicate = ST:type)}";
    	
    	
    	
    	// =============================Step Five===================================
    	// --------------------------Find the Range Information of This Property Type Relation---------------------------------
    	//String szQuery =  "select * where {?Subject ?Predicate ?Object filter(?Subject = <http://dbpedia.org/ontology/starring> && ?Predicate = <http://www.w3.org/2000/01/rdf-schema#range>)}";
    	
    	// =============================Step Six===================================
    	// --------------------------Search for Type object class in this Knowledge Graph---------------------------------
    //	String szQuery =  "select distinct ?Object {?Subject ?Predicate ?Object filter(?Subject = <http://dbpedia.org/ontology/starring> && ?Predicate = <http://www.w3.org/2000/01/rdf-schema#range>)}";
    	//String szQuery =  "select distinct ?Object {?Subject ?Predicate ?Object filter(?Subject = <http://dbpedia.org/ontology/director> && ?Predicate = <http://www.w3.org/2000/01/rdf-schema#range>)}";
    	
    	
    	// =============================Step Final===================================
    	// --------------------Search for Type object class in this Knowledge Graph---------------------------------
    	
    	
    	//================================FREBASE=========================================
    //	String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Subject = <http://rdf.freebase.com/ns/g.11vjz1ynm> && ?Predicate=<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>)} LIMIT 1";
    //	String szQuery = "select * where {?Subject ?Predicate ?Object filter(?Predicate = <http%3A%2F%2Fwww.wikidata.org%2Fprop%2Fdirect%2FP161> && ?Object=<http%3A%2F%2Fwww.wikidata.org%2Fentity%2FQ35332>)} LIMIT 10";
    	
    		//?predicate=http%3A%2F%2Fwww.wikidata.org%2Fprop%2Fdirect%2FP161
    	//	?object=http%3A%2F%2Fwww.wikidata.org%2Fentity%2FQ35332
    	//====================================================================================================
    	
    	//String szQuery =	"SELECT ?film ?title ?name WHERE {  ?film wdt:P161 wd:Q35332 ; wdt:P57 ?director . ?director rdfs:label ?name .  ?film rdfs:label ?title .  FILTER LANGMATCHES(LANG(?title), "EN") FILTER LANGMATCHES(LANG(?name),  "EN")	}";
    	
        // Some Arguments here 
        if (args != null && args.length == 1) {
            szQuery = new String(
                    Files.readAllBytes(Paths.get(args[0])),
                    Charset.defaultCharset());
        }

        // DBPedia Endpoint
      String szEndpoint = "http://dbpedia.org/sparql";
        //String szEndpoint = " https://developers.google.com/freebase/";
        //String szEndpoint ="https://query.wikidata.org/bigdata/ldf";
        //String szEndpoint = "http://dbpedia.org";
        // Querying from online DBPedia knowledge graph
        try {
        	NTRC q = new NTRC();
            q.queryEndpoint(szQuery, szEndpoint);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}