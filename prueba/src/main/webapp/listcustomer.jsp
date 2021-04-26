<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function checkBoxValidation()
{
	var checked =0;
	
for(var i=0; i < document.form1.requestList.length; i++)
{
if(document.form1.requestList[i].checked)

{
	checked=checked+1;
}
}
if(checked >0){
	return true;
	
}else{
	return false;}
}
</script>
<style>
    body{font-size:20px;font-family: Arial;background-color:#F4F4F4;}
    #contenedor{width:980px;margin:auto;border: 1px solid #DCDCDC;background-color: #FFF;padding: 25px 40px;border-radius: 5px;}
    h2{margin-top:0px;margin: 0px -52px 80px -52px;padding: 0px 0px 0px 52px;color: #FFF;font-style: italic;font-weight: bold;font-size: 23px;background-color: #ED1A24;height: 40px;line-height: 37px;position: relative;}
    h2:after{position: absolute;left: 0px;bottom: -12px;content: "";border-top: 12px solid #C20912;border-left: 12px solid transparent;}
    h2:before{position: absolute;right: 0px;bottom: -12px;content: "";border-top: 12px solid #C20912;border-right: 12px solid transparent;}
    .clearfix::after {clear: both; content: " ";display: block;font-size: 0px; height: 0px;line-height: 0; visibility: hidden; width: 0px;}
    .clearfix::before, .clearfix::after { content: " ";  display: table;}
    .ejemplo-wrapper{margin-bottom:110px;}
    
    /*Estilo Base*/
    ul{list-style:none;margin:0px;padding:0px;clear:both;}
    ul li{margin:0px 10px;padding:0px;float:left;}
    ul li a{display:block;text-decoration:none}
    
   
    /*Ejemplo 3*/
    ul#ejm3 li a{
        position: relative;
        color:#FFF;
        overflow:hidden;
    }
    ul#ejm3 li a:before{
        position: absolute;
        top: 0px;
        left: 0px;
        z-index: 1;
        padding: 10px 40px;
        background: none repeat scroll 0% 0% #FFF;
        color: #0F7C67;
        content: attr(title);
        transition: transform 0.3s ease 0s;
    }
    ul#ejm3 li a span{
        display: block;
        position: relative;
        padding: 10px 40px;
        background: none repeat scroll 0% 0% #003E8B;
        transition: transform 0.3s ease 0s;
        z-index: 2;
    }

    ul#ejm3 li a:hover span,ul#ejm3 li a:focus span{
        transform: translatex(100%);
    }
    
   
 </style>
</head>
<body>
<spring:url var="crear" value="/index.jsp">
</spring:url>
<spring:url var="consultar" value="/listcustomer">
</spring:url>
	 <ul id="ejm3">
	             <li><a href="${crear}" title="Crear"><span>Crear</span></a></li>
	             <li><a href="${consultar}" title="Consultar"><span>Consultar</span></a></li>
	              

                 </ul>
                  <div class="clearfix"></div>

<br>
<br>

 




       <form name="form1" onsubmit="return checkBoxValidation()" action="updateCustomer" method="post">



<table border="1">
			<tr>
				<th >ID</th>
				<th>NOMBRE</th>
				<th>APELLIDO</th>
			    <th>PROCESADO</th>
			    <th>PROCESAR</th>
				
			</tr>
			<c:forEach var="customer" items="${list}">
			<tr>
				<td>${customer.id}</td>
				<td>${customer.name}</td>
				<td>${customer.lastName}</td>
				<td>${customer.isProcess}</td>
				<td><input type="checkbox" name="requestList" value="${customer.id}"  />
				</tr>
			    </c:forEach>
			    </table>
			    <p><input type="submit" value="Procesar"/>
			    
			    </form>

    
	
			
	

</body>
</html>