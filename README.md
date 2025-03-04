# Testing requests

### correct credentials, valid case
curl -v POST "http://localhost:8080/case/start" \
-H "Content-Type: application/json" \
-u "user:pass" \
-d '{
"caseId": "12345",
"caseType": "IMPORTANT_CASE"
}'

### incorrect credentials
curl -v POST "http://localhost:8080/case/start" \
-H "Content-Type: application/json" \
-u "userasd:pass" \
-d '{
"caseId": "12345",
"caseType": "IMPORTANT_CASE"
}'

### correct credentials, invalid case
curl -v POST "http://localhost:8080/case/start" \
-H "Content-Type: application/json" \
-u "user:pass" \
-d '{
"caseId": "12345",
"caseType": "RANDOM_CASE"
}'

