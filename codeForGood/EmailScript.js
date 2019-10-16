function loadData(){
  var spreadsheet = SpreadsheetApp.getActiveSheet();
  var row = Session.getActiveUser().getRowIndex();
  var range = spreadsheet.getRange('C','C')
  var score = Session.getActiveUser().getCell(row, 0)
  var email = Session.getActiveUser().getEmail();
  var body = "Thank you for submitting a response! You hvae been placed into " + this.getPlacement();
}

function onFormSubmit(e) {
  this.loadData();
  GmailApp.sendEmail(email, "Poder Class Placement", body);
                     }

function getPlacement(){
  if (score == 0)
    return "Ventures 0"
   else if (score < 4)
     return "Ventures Basic";
  else if (score < 13)
    return "Ventures 1";
  else if (score < 22)
    return "Ventures 2";
  else if (score < 31)
    return "Ventures 3";
  else return "Ventures 4";

} // Tihs script would generate an email to the newly register user containing their class placement. It didn't make the final implementation due to GCP limitations
