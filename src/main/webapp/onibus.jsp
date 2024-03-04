<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css"/>
<title>Onibus</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp"/>
	</div>
	<br />
	<div align="center" class="container">
		<form action="onibus" method="post">
			<p class="title">
				<b>Onibus</b>
			</p>
			<table>
			<tr>
				<td colspan="3">
					<input class="id_input_data" type="text" id="placa"
					name="placa" placeholder="Placa" value='<c:out value="${onibus.placa}"></c:out>'>
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Buscar">
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="text" id="marca" name="marca" placeholder="Marca" value='<c:out value="${onibus.marca}"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="number" id="ano" name="ano" placeholder="Ano" value='<c:out value="${onibus.ano}"></c:out>'
				 min="0" step="1">
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="text" id="descricao" name="descricao" placeholder="Descricao" value='<c:out value="${onibus.descricao}"></c:out>'>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" id="botao" name="botao" value="Cadastrar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Alterar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Excluir">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Listar">
				</td>
			</tr>
			</table>
		</form>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro}">
			<H2><b><c:out value="${erro}"></c:out></b></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida}">
			<H3><b><c:out value="${saida}"></c:out></b></H3>
		</c:if>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty listaonibus}">
			<table class="table_round">
				<thead>
					<tr>
						<th>Placa</th>
						<th>Marca</th>
						<th>Ano</th>
						<th>Descrição</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${listaonibus}">
						<tr>
							<td><c:out value="${o.placa}"/></td>
							<td><c:out value="${o.marca}"/></td>
							<td><c:out value="${o.ano}"/></td>
							<td><c:out value="${o.descricao}"/></td>
						</tr>
					</c:forEach>
			
				</tbody>
			</table>
		</c:if>
	<div align="center">	
</body>
</html>