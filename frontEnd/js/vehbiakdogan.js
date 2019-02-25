/**
* 	@author: Vehbi AKDOGÂAN
* 	@mail: akdoganvehbi@gmail.com || info@vehbiakdogan.com
* 	@website: vehbiakdogan.com 
*/
$(() =>{ 
	window.serverStatus = true;
	// api url
	var apiUrl = "http://localhost:4444/";
	
	
	// form url
	var formUrl = {
		"login" :["isLogin","POST"],
		"register":["user","POST"],
		"addTodo":["todo","POST"],
		"editTodo":["todo","PUT"],
		"deleteTodo":["todo","DELETE"],
		"addTodoItem":["todoItem","POST"]
		
	};
	
	// toastr options 
	
	toastr.options = {
	  "closeButton": false,
	  "debug": false,
	  "newestOnTop": false,
	  "progressBar": false,
	  "positionClass": "toast-top-right",
	  "preventDuplicates": false,
	  "onclick": null,
	  "showDuration": "300",
	  "hideDuration": "1000",
	  "timeOut": "5000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}
	 
	 
	 
	 
	 
	 // send api ajax 
	
	$.apiSend = (url,type,data,callback)  => {
		if(serverStatus) {
			$.ajax({
			    url: url,
			    type: type,
			    data: data,
			    processData: false,
	    		contentType: 'application/json',
			    dataType:'json',
			    success: function (response) {
			    	
			    	callback(response);
						
						
			    },
			    error: function(error){
			    	console.log(error);
			    	callback("error");
			    }
			});
		}
	}
	 
	 
	 // server running control
	var serverInterval = setInterval(()=>{
		$.ajax({
			url:apiUrl,
			type:"GET",
			success: (res) =>{
			if(res == "error") {
				$(".error").html(' Server not Running: '+apiUrl).css({"display":"flex"});
				serverStatus = false;
			}else {
				serverStatus = true;
				$(".error").remove();
				clearInterval(serverInterval);
			}
			
		},
		error:() => {
				$(".error").html(' Server not Running: '+apiUrl).css({"display":"flex"});
				serverStatus = false;
		}});
	},10000); // 10 second control
	 
	 // cookie Control
	 $.cookieControl = () =>  {
	 	
	 	var url = location.href.split("/");
	 		url = url[url.length-1].replace(".html","");
	 	var allowedPage = ["login","register","index"]; 
	 	if(localStorage.getItem("isLogin") != null &&  allowedPage.indexOf(url) > -1) {
			location.href="home.html";
		}else if(localStorage.getItem("isLogin") == null &&  allowedPage.indexOf(url) == -1) {
			location.href = "login.html";
		}
	 	
	 	
	 	
	 }
	 $.cookieControl();
	 
	 
	 
	 $.userHtml = () => {
	 	
	 	var user = JSON.parse(localStorage.getItem("user"));
	 	$(".userName").html(user.username);
	 	$(".userIdText").val(user.id);
	 	
	 	
	 	
	 }
	 $.userHtml();
	 
	 
	 // array to object
	 
	 $.arrayToObject = function(data) {
	 	var obj = "{";
	 	
	 	for(var i = 0; i<data.length;i++) {
			if(data[i].name == undefined) 
				break;
			obj = obj+'"'+data[i].name+'":'+ '"'+data[i].value+'",';
		}
		
		obj = obj.replace(/,$/, '')+"}";
		return obj;
	 	
	 	
	 }
	 
	 // login function 
	 
	 $.login = (res) => {
	 	var user = res.user;
	 	if(res.code=="OK") {
			$(".formAlert").addClass("alert-success").html("Login is Success!").fadeIn();
		 	localStorage.setItem("id",user.id);
		 	localStorage.setItem("user",JSON.stringify(user));
		 	localStorage.setItem("isLogin",true);
		 	setTimeout(function(){
				location.href="home.html";
			},1000);
		}else {
			$(".formAlert").addClass("alert-danger").html("Login is Failure!").fadeIn();
		}
	 	
	 	
	 }
	 
	 // logout function 
	 
	 $.logOut = () =>  {
	 	
	 	delete localStorage.isLogin;
	 	location.href = "login.html";
	 	
	 	return false;
	 }
	 
	 // register function 
	 $.register = (res) =>  {
	 	if(res.code == "OK") {
			$(".formAlert").addClass("alert-success").html("Register is Success!").fadeIn();
		 	setTimeout(function(){
				location.href="login.html";
			},1000);
		}else {
			$(".formAlert").addClass("alert-danger").html("Register is Fail!").fadeIn();
		}
	 	
	 	
	 }
	 
	 // add Todo
	 $.addTodo = (res) =>  {
	 	if(res.code == "OK") {
			$(".formAlert").addClass("alert-success").html("Todo Added Success!").fadeIn();
			$.apiSend(apiUrl+"userTodo/"+localStorage.getItem("id"),"GET",'',$.listTodo);
			
			setTimeout(()=>{
				$('#addTodoModal').modal('hide');
				$(".formAlert").removeClass("alert-success").html("").fadeIn();
				$('#addTodoModal form')[0].reset();
			},500);
		}else {
			$(".formAlert").addClass("alert-danger").html("Todo  Added Failure!").fadeIn();
		}
	 	
	 }
	 
	 // edit todo
	 $.editTodo = (res) => {
	 	 
	 	if(res.code == "OK") {
			$(".formAlert").addClass("alert-success").html("Todo Edited Success!").fadeIn();
			$.apiSend(apiUrl+"userTodo","POST",'{"userId":'+localStorage.getItem("id")+'}',$.listTodo);
			
			setTimeout(()=>{
				$("#editTodoModal").modal("hide"); 
				$(".formAlert").removeClass("alert-success").html("").fadeIn();
				$('#editTodoModal form')[0].reset();
			},500);
		}else {
			$(".formAlert").addClass("alert-danger").html("Todo  Edited Failure!").fadeIn();
		}
	 	
	 }
	
	 // Delete Todo 
	 $.deleteTodo = (id,dis) => {
	 	$.apiSend(apiUrl+"todo","DELETE",'{"id":'+id+'}',(res) =>  {
	 		if(res.code == "OK")
	 			$(dis).parent().parent().fadeOut();
	 	});
	 	
	 	return false;
	 };
	 
	 // add todo item
	$.addTodoItem = (res) =>  {
	 	if(res.code == "OK") {
			$(".formAlert").addClass("alert-success").html("Todo Item Added Success!").fadeIn();
			setTimeout(()=>{
				$('#addTodoItemModal').modal('hide');
				$(".formAlert").removeClass("alert-success").html("").fadeIn();
				$('#addTodoItemModal form')[0].reset();
			},500);
		}else {
			$(".formAlert").addClass("alert-danger").html("Todo Item  Added Failure!").fadeIn();
		}
	 	
	 }
	 
	 
	 
	 
	 // form subimt 
	$("form").submit(function() {
		
		var form = $(this).data("form"),
			url = apiUrl+formUrl[form][0],
			method = formUrl[form][1],
			data = $.arrayToObject($(this).serializeArray());
		if(form == "login") {
			$.apiSend(url,method,data,$.login);
		}else if(form == "register") {
			$.apiSend(url,method,data,$.register);
		}else if(form == "addTodo") {
			$.apiSend(url,method,data,$.addTodo);
		}else if(form == "editTodo") {
			$.apiSend(url,method,data,$.editTodo);
		}else if(form == "addTodoItem") {
			$.apiSend(url,method,data,$.addTodoItem);
		}
 		
		
		
		return false;
	});
	
	
	
	// home page  list todo
	$.listTodo = (res) => { 
		$(".todoList ").html("");
		if(res.code == "OK") {  
			 if(res.count == 0) {
			 	$(".todoList").html(" <tr> <td colspan='4' class='text-center'> Todo Not Found</td></tr>");
			 }else {
			 	
			 	for(var i = 0;i<res.count;i++) {
					$(".todoList").append(" <tr> <td>"+(i+1)+"</td> <td>"+res.todo[i].name+"</td><td>"+res.todo[i].createdTime+"</td><td> <a href='' class='label label-info editTodo' onClick='$.showTodoModal("+res.todo[i].id+",this);return false;' data-toggle='tooltip' title='Show Todo'> <i class='fa fa-eye'></i></a>  <a href='' class='label label-default editTodo' onClick='$.addTodoItemModal("+res.todo[i].id+",this);return false;' data-toggle='tooltip' title='Add Todo Item'> <i class='fa fa-plus'></i></a> <a href='' class='label label-success editTodo' onClick='$.editTodoModal("+res.todo[i].id+",this);return false;' data-toggle='tooltip' title='Edit Todo'> <i class='fa fa-edit'></i></a> <a href='' class='deleteTodo label label-danger'  onClick='$.deleteTodo("+res.todo[i].id+",this);return false;' data-toggle='tooltip' title='Delete Todo'> <i class='fa fa-trash'></i></a></td></tr>");
				}
			 	
			 }
			 $('[data-toggle="tooltip"]').tooltip();
		}else { 
			$(".todoList").html(" <tr> <td colspan='4' class='text-center alert aler-danger'> Connection Error</td></tr>");
		}
	}
	$.apiSend(apiUrl+"userTodo/"+localStorage.getItem("id"),"GET",'',$.listTodo);
	
	 
	 
	 // Edit Todo Modal
	 $.editTodoModal = (id,dis) => {
	 	
	 	$.getJSON(apiUrl+"todo/"+id,(res) => {
	 		$(".todoName").val(res.todo.name);
	 		$(".todoIdText").val(res.todo.id);
	 		$(".todoCreatedTimeText").val(res.todo.createdTime);
	 		
	 		$("#editTodoModal").modal("show");
	 	});
	 	
	 	
	 }
	 
	 // Add Todo Item Modal
	 $.addTodoItemModal = (todoId,dis) => {
	 	
	 	
	 	$.getJSON(apiUrl+"todoItem/"+todoId,(res) => { 
	 		$(".todoIdText").val(res.todoId);
	 		
	 		for(var i = 0; i<res.count;i++) {
				$("select.todoItems").append(' <option value="'+res.todo[i].itemId+'">'+res.todo[i].title+'</option>');
			}
	 		$("#addTodoItemModal").modal("show");
	 		$('#addTodoItemModal').on('hidden.bs.modal',  () =>  {
			    $("select.todoItems").html(' <option value="0"> No Attached </option>');
			});

	 	});
	 }
	 
	 $.showTodoModal = (id,dis) => {
	 	$("#showTodoModal").modal("show");
	 	$.todoItemList(id);
	 	
	 }
	 
	 // todo item list by id 
	 $.todoItemList = (id) => {
	 	$.getJSON(apiUrl+"todoItem/"+id,(res) => {
	 		$(".todoItemList ").html("");
			if(res.code == "OK") {  
				 if(res.count == 0) {
				 	$(".todoItemList").html(" <tr> <td colspan='7' class='text-center'> Item Not Found</td></tr>");
				 }else {
				 	
				 	for(var i = 0;i<res.count;i++) {
				 		check = res.todo[i].status ? "checked" : "";
				 		checkbox = '<input type="checkbox" '+check+' onChange="$.todoItemStatusUpdate('+res.todo[i].itemId+',this);" class="check" id="check'+i+'"/><label for="check'+i+'" style="--d: 23px;"><svg viewBox="0,0,50,50"><path d="M5 30 L 20 45 L 45 5"></path></svg></label>';
						$(".todoItemList").append(" <tr> <td>"+(i+1)+"</td> <td>"+res.todo[i].title+"</td>"+
						"<td>"+res.todo[i].desctription+"</td>"+
						"<td>"+res.todo[i].startTime+"</td>"+
						"<td>"+res.todo[i].endTime+"</td>"+
						"<td class='status'>"+(res.todo[i].status == false ? "Incomplete" : "Complate")+"</td>"+
						"<td> "+checkbox+" <a href='' class='deleteTodo label label-danger'  onClick='$.deleteTodoItem("+res.todo[i].id+",this);return false;' data-toggle='tooltip' title='Delete Todo'> <i class='fa fa-trash'></i></a></td></tr>");
					}
				 	$('.dataTable').DataTable();
				 } 
			}else { 
				$(".todoItemList").html(" <tr> <td colspan='4' class='text-center alert aler-danger'> Connection Error</td></tr>");
			}
	 	});
	 }
	 
	 // todo item status update
	 
	 
	 $.todoItemStatusUpdate = (itemId,dis) => {
	 	var status = false,
	 		msg = "Incomplete";
	 	if($(dis).prop("checked")) {
			status = true;
			msg = "Complate";
		}
	 		
	 	
	 	$.apiSend(apiUrl+"todoItem/"+itemId,"PUT",'{"status":'+status+'}',(res) =>{
	 		if(res.code == "GONE") {
				toastr["error"]("Please Complete Addiction");
				$(dis).removeAttr("checked");
			}else {
				$(dis).parent().parent().find(".status").html(msg);
				toastr["success"]("Successfully Completed");
			}
	 		
	 		
	 	});
	 }
	 
	
	
	
	
	
	
	
	
	
	
});