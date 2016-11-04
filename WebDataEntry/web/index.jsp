<%-- 
    Document   : index
    Created on : Oct 21, 2016, 9:59:56 AM
    Author     : pdlo003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="WebDataEntry.pdlo.jsp.*"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<% Class.forName("org.postgresql.Driver"); %>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Data for CIM</title>
        
         <%
            int result = 0;
            if (request.getParameter("enterData") != null) {
                                 
                String name = new String();
                String ntName = new String();
                String ntDesBox = new String();
                String ntaName = new String();
                String ntaDesBox = new String();
                String uuidBox = new String();
                boolean entUUID = false;
                
                if (request.getParameter("uuidEnt") != null) {
                    if (request.getParameter("uuidEnt") == "false") {
                        entUUID = false;
                    }
                    else if (request.getParameter("uuidEnt") == "true") {
                        entUUID = true;
                        if (request.getParameter("enter_uuidBox") != null) {
                            uuidBox = request.getParameter("enter_uuidBox");
                        }
                    }
                }
                
                if (request.getParameter("n_nameBox") != null) {
                    name = request.getParameter("n_nameBox");
                }
                if (request.getParameter("nt_nameBox") != null) {
                    ntName = request.getParameter("nt_nameBox");
                }
                if (request.getParameter("nt_desBox") != null) {
                    ntDesBox = request.getParameter("nt_desBox");
                }
                if (request.getParameter("nta_nameBox") != null) {
                    ntaName = request.getParameter("nta_nameBox");
                }
                if (request.getParameter("nta_desBox") != null) {
                    ntaDesBox = request.getParameter("nta_desBox");
                }
                /* instantiate the connection and move over the data */
                DBConn insData = new DBConn();
                
               result = insData.sendToDB(name, ntName, ntDesBox, ntaName, ntaDesBox, uuidBox, entUUID);
            }
            

                %>
    </head>
    <body onLoad="displayResults()">
        <h1>Enter Data for CIM</h1>
        <form name="myForm" action="index.jsp" method="POST">
         <fieldset>
                Name <br>
                <input type="text" name="n_nameBox">
        </fieldset>
        <fieldset>
            Name Type<br><br>
            Name<br>
            <input type="text" name="nt_nameBox"><br><br>
            Description<br>
            <input type="text" name="nt_desBox"><br>
        </fieldset>
        <fieldset>
                Name Type Authority<br><br>
                Name<br>
                <input type="text" name="nta_nameBox"><br><br>
                Description<br>
                <input type="text" name="nta_desBox"><br>
            </fieldset>
            <fieldset>
                <input type ="radio" name ="uuidEnt" value ="false" checked> Randomly Generate UUID<br>
                <input type="radio" name="uuidEnt" value="true"> Enter UUID Here:
                <input type="text" name="enter_uuidBox"><br><br>
            </fieldset>
            <input type="hidden" name="hidden" value="<%= result %>" />
            <input type="submit" value="Enter" name="enterData"> 

        </form>
            <SCRIPT>
            <!--
            function displayResults()
            {
                if (document.myForm.hidden.value == 1) {
                    alert("Data inserted!");
                    
                }
            }
            // -->
            </SCRIPT>
    </body>
</html>
