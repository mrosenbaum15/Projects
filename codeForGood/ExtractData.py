#inPksGmfJGWW8uC7o4JbQJ79T
from simple_salesforce import Salesforce
sf = Salesforce(username='serenesaldana-uast@force.com', password='Serene1215', security_token='inPksGmfJGWW8uC7o4JbQJ79T')
sf.Contact.create({'LastName':'Smith','Email':'example@example.com'})
# this ended up as a dead end because the free version of salesforce, which we were using doesn't exteral apis
