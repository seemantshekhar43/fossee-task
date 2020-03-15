$(document).ready(function () {
    $('input[type=file]').change(function () {
        var f = this.files;
        var el = $(this).parent();
        if (f.length > 1) {
            console.log(this.files, 1);
            el.text('Sorry, multiple files are not allowed');
            return;
        }
        $('.file_name').text(f[0].name);

    });

    $('input[type=file]').on('focus', function () {
        $(this).parent().addClass('focus');
    });

    $('input[type=file]').on('blur', function () {
        $(this).parent().removeClass('focus');
    });

});

$(document).ready(function () {
    $(".reset-btn").click(function () {
        $("#myForm").trigger("reset");
        $('.file_name').text("Drop a file or click to select one");
    });
});
