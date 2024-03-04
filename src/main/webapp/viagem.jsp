<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css"/>
<title>Viagem</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp"/>
	</div>
	<br />
	<div align="center" class="container">
		<form action="viagem" method="post">
			<p class="title">
				<b>Viagem</b>
			</p>
			<table>
			<tr>
				<td colspan="3">
					<input class="id_input_data" type="number" min="0" step="1" id="codigo"
					name="codigo" placeholder="Codigo" value='<c:out value="${viagem.codigo}"></c:out>'>
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Buscar">
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<select class="input_data" id="onibus" name="onibus">
						<option value="0">Escolha um onibus</option>
						<c:forEach var="o" items="${onibusLista}">
							<option value="${o.placa}">
								<c:out value="${o.placa}"/>
							</option>
						</c:forEach>

					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<select class="input_data" id="motorista" name="motorista">
						<option value="0">Escolha um motorista</option>
						<c:forEach var="m" items="${motoristas}">
							<option value="${m.codigo}">
								<c:out value="${m.nome}"/>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="number" id="hora_saida" name="hora_saida" placeholder="Hora de Saída" value='<c:out value="${viagem.horaSaida}"></c:out>'
				 min="0" step="1">
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="number" id="hora_chegada" name="hora_chegada" placeholder="Hora de chegada" value='<c:out value="${viagem.horaChegada}"></c:out>'
				 min="0" step="1">
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="text" id="partida" name="partida" placeholder="Partida" value='<c:out value="${viagem.partida}"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<input class="input_data" type="text" id="destino" name="destino" placeholder="Destino" value='<c:out value="${viagem.destino}"></c:out>'>
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
			<tr>
				<td colspan="3">
					<input type="submit" id="botao" name="botao" value="Desc do Onibus">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" id="botao" name="botao" value="Desc da Viagem">
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
		<c:if test="${not empty viagens}">
			<table class="table_round">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Onibus</th>
						<th>Motorista</th>
						<th>Hora de Saída</th>
						<th>Hora de Chegada</th>
						<th>Partida</th>
						<th>Destino</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="v" items="${viagens}">
						<tr>
							<td><c:out value="${v.codigo}"/></td>
							<td><c:out value="${v.onibus}"/></td>
							<td><c:out value="${v.motorista}"/></td>
							<td><c:out value="${v.horaSaida}"/></td>
							<td><c:out value="${v.horaChegada}"/></td>
							<td><c:out value="${v.partida}"/></td>
							<td><c:out value="${v.destino}"/></td>
						</tr>
					</c:forEach>
			
				</tbody>
			</table>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty descViagens}">
			<table class="table_round">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Motorista</th>
						<th>Placa do Onibus</th>
						<th>Hora de Saída</th>
						<th>Hora de Chegada</th>
						<th>Partida</th>
						<th>Destino</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="v" items="${descViagens}">
						<tr>
							<td><c:out value="${v.codigo}"/></td>
							<td><c:out value="${v.motorista.nome}"/></td>
							<td><c:out value="${v.onibus.placa}"/></td>
							<td><c:out value="${v.horaSaidaFormat}"/></td>
							<td><c:out value="${v.horaChegadaFormat}"/></td>
							<td><c:out value="${v.partida}"/></td>
							<td><c:out value="${v.destino}"/></td>
						</tr>
					</c:forEach>
			
				</tbody>
			</table>
		</c:if>
	</div>	
</body>
</html>