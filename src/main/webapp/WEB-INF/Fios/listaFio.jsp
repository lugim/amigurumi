<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Fios</title>
</head>
<body>

	<div>
		<table>
			<thead>
				<th>Marca</th>
				<th>Tipo de Fio</th>
				<th>Tipo de Cor</th>
				<th>Cor</th>
				<th>Código da Cor</th>
				<th>Metragem inicial</th>
				<th>Peso inicial</th>
			</thead>
			
			 <c:forEach items="${fios}" var="fio">
				<tr >
					<td>${fio.marca}</td>
					<td>${fio.tipoFio}</td>
					<td>${fio.tipoCor}</td>
					<td>${fio.nomeCor}</td>
					<td>${fio.codigoCor}</td>
					<td>${fio.metragemInicial}</td>
					<td>${fio.pesoInicial}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>