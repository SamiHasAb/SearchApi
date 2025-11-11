GET: http://localhost:8080/api/employees  
GET: http://localhost:8080/api/employees/search?query=Developer&page=0&size=20  
GET:  http://localhost:8080/api/employees/1  

POST: http://localhost:8080/api/employees  
PUT:  http://localhost:8080/api/employees/1  
DELETE: http://localhost:8080/api/employees/1

  
**Search**

GET: http://localhost:8080/api/employees/search?query=John&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=Engineering&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=Manager&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=company.com&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=Smith&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=Market&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=30&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=50000&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=2023&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=01&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=2023&page=0&size=20  
GET: http://localhost:8080/api/employees/search?query=01&page=0&size=20  

GET: http://localhost:8080/api/employees/advanced-search?name=John&minAge=25&maxAge=40&minSalary=50000&maxSalary=100000&department=Engineering&position=Developer&active=true&page=0&size=20  
GET: http://localhost:8080/api/employees/advanced-search?name=John&minAge=30&maxAge=45&page=0&size=20  
GET: http://localhost:8080/api/employees/advanced-search?position=Manager&active=true&page=0&size=20  
GET: http://localhost:8080/api/employees/advanced-search?position=Manager&active=true&page=0&size=20  
GET: http://localhost:8080/api/employees/advanced-search?name=Smith&minAge=25&maxAge=35&minSalary=50000&maxSalary=80000&page=0&size=20
GET: http://localhost:8080/api/employees/advanced-search?name=John&minAge=25&maxAge=40&minSalary=50000&maxSalary=100000&department=Engineering&position=Developer&active=true&page=0&size=20   
GET: http://localhost:8080/api/employees/advanced-search?name=John&minAge=30&maxAge=45&page=0&size=20  
GET: http://localhost:8080/api/employees/advanced-search?minSalary=60000&maxSalary=120000&department=Engineering&page=0&size=20     
GET: http://localhost:8080/api/employees/advanced-search?position=Manager&active=true&page=0&size=20   
GET: http://localhost:8080/api/employees/advanced-search?name=Smith&minAge=25&maxAge=35&minSalary=50000&maxSalary=80000&page=0&size=20   
GET:    
GET:    
GET:    




