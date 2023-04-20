<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>

  <header>
      		<nav class="navbar navbar-expand-md navbar-dark"
      			style="background-color: #ADD8E6">
      			<div>
      				<h3>Products<h3>
      			</div>



      		</nav>
      </header><br><br><br>

	<div class="row">


		<div class="container">


			<h3 class="text-center">List of Products</h3>
			<hr>

			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
                        <th>Product ID</th>
						<th>Product Name</th>
						<th>Price</th>
						<th>Quantity Available</th>
						<th>Quantity</th>

					</tr>
				</thead>
				<tbody>

					<c:forEach var="prod" items="${product}">

						<tr>
						    <form action="addPrduct" method ="get">
							<td><input type="hidden" name="id" value="${prod.id}" /><c:out value="${prod.id}" /></td>
							<td><input type="hidden" name="pName" value="${prod.pName}" /><c:out value="${prod.pName}" /></td>
							<td><input type="hidden" name="price" value="${prod.price}" /><c:out value="${prod.price}" /></td>
							<td><input type="hidden" name="qty" value="${prod.qty}" /><c:out value="${prod.qty}" /></td>
							<td><input type="text" name="uQty"></td>

						   <td><input type="submit" value="add">
                        </form>
						</tr>

					</c:forEach>
					<!-- } -->
					<form action="listAdded" method="post">
                                     <input type="submit" value="Cart" class="btn btn-primary btn-lg"></a>

				</tbody>

			</table>

			<center><h3><c:out value="${abc}" /></h3></center>
		</div>
	</div>





    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
