 $(function () {

  $.datepicker.regional['ru'] = {
    closeText: 'Закрыть',
    prevText: '<Пред',
    nextText: 'След>',
    currentText: 'Сегодня',
    monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь',
    'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
    monthNamesShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн',
    'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'],
    dayNames: ['воскресенье', 'понедельник', 'вторник', 'среда', 'четверг', 'пятница', 'суббота'],
    dayNamesShort: ['вск', 'пнд', 'втр', 'срд', 'чтв', 'птн', 'сбт'],
    dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
    dateFormat: 'dd.mm.yy',
    firstDay: 1,
    isRTL: false
  };
  $.datepicker.setDefaults($.datepicker.regional['ru']);


  $("#datepicker").datepicker();
  $("#datepicker1").datepicker();
                                        //слайдер


  // We can attach the `fileselect` event to all file inputs on the page
  $(document).on("change", ":file", function() {
    var input = $(this),
    numFiles = input.get(0).files ? input.get(0).files.length : 1,
    label = input
    .val()
    .replace(/\\/g, "/")
    .replace(/.*\//, "");
    input.trigger("fileselect", [numFiles, label]);
  });

  // We can watch for our custom `fileselect` event like this
  $(document).ready(function() {
    $(":file").on("fileselect", function(event, numFiles, label) {
      var input = $(this)
      .parents(".input-group")
      .find(":text"),
      log = numFiles > 1 ? numFiles + " files selected" : label;

      if (input.length) {
        input.val(log);
      } else {
        if (log) alert(log);
      }
    });
  });


    $( "#tabs" ).tabs({
      beforeLoad: function( event, ui ) {
        ui.jqXHR.fail(function() {
          ui.panel.html(
            "Couldn't load this tab. We'll try to fix this as soon as possible. " +
            "If this wouldn't be a demo." );
        });
      }
    });


});