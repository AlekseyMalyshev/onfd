<%-- 
    Document   : nav_bar
    Created on : Jul 13, 2015, 4:28:41 PM
    Author     : Aleksey Malyshev
--%>
<nav>
	<img src="/img/onfd_logo.png" />
	<div class="name">Online Fitting Database</div>
	<ul>
		<li>${user.nameFirst}</li>
		<li><a href="/vendor">Home</a></li>
		<li><a href="/vendor/edit">Edit ${user.nameFirst}</a></li>
		<li><a href="/logout">Logout</a></li>
	</ul>
</nav>
