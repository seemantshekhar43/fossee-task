
$(document).ready(function() {
	$('input[type=file]').change(function() {
			//console.log(this.files);
			var f = this.files;
			var el = $(this).parent();
			if (f.length > 1) {
					console.log(this.files, 1);
					el.text('Sorry, multiple files are not allowed');
					return;
			}
			$('#file_name').text(f[0].name);
			// el.removeClass('focus');
//			el.html(f[0].name + '<br>' +
//					'<span class="sml">' +
//					'type: ' + f[0].type + ', ' +
//					Math.round(f[0].size / 1024) + ' KB</span>');
	});

	$('input[type=file]').on('focus', function() {
			$(this).parent().addClass('focus');
	});

	$('input[type=file]').on('blur', function() {
			$(this).parent().removeClass('focus');
	});

});

$(document).ready(function(){
    $(".reset-btn").click(function(){
        $("#myForm").trigger("reset");
        $('#file_name').text("Drop a file or click to select one");
    });
});
