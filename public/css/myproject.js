
$(function() {
  $('#sortable').sortable({
    placeholder: 'ui-state-highlight',
    stop: function(event, ui) {
      $.get("/questionnaire/update_positions?" 
            + $(this).sortable('serialize')
            + "&shortname=ko&version=1");            
    }
 });
  $('#sortable').disableSelection();
});
