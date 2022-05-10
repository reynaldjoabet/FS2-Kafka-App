#!/bin/bash
echo " make sure the server is running"
echo " Sending requests to localhost:8080/api/v1/event"

for i in {1..10}
 do
  curl --request POST -sL -i -v --header "Cotent-Type: application/json" --url 'http://localhost:8080/api/v1/event' --data  '{"userName": "Beatice",
    "message": "I have completed the server part of the app",
    "mood": "I feel gret today😛🧐",
    "todo": "Tomorrow, I want to just sleep $i times "
   }'


done
