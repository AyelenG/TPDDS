	$(document).ready(function() {
		$( ".empresas" ).change(function() {  
			var str = $("#empresa").val();
			$.ajax({
				type: "GET",
				url:"/periodos",
				data:{'id' : str},
				error: function(){
					alert("Error, recargue la páginas");
				},
				success: function(data){
					$("#periodos").replaceWith(data);
				}
			});

		});	
	});

