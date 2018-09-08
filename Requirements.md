### Roles:
Product owner
- Rolf-Helge Pfeiffer

Scrum master
- Michael Veilis

Test manager
- Martin Hansen

Architecture manager
- Lasse Hansen

Developers
- Lasse Hansen
- Martin Hansen
- Michael Veilis

### Hacker news, reddit like site Requirement:

	1. Register Users
	2. Comments / Discussions (Required to be logged in / Viewable otherwise)
	3. Upvote and Downvote posts (downvote disabled till 500 points). 
	4. Karma system, combined up and down vote points
	5. Flag as spam feature
	6. Web-application frontend. 
	7. Backend
	8. Database for storing users and discussions (including karma).
	9. HTTP Api for simulator program that can post stories etc.
	10. API access for external program to do statistics (Unspecified yet).
	11. Published to a public server - has to have at least 95% uptime. 
	12. Don't lose content. Have a buffer for incomming content during downtime etc.  



To do:
	Divide into functional and non-functional. 
	Specify what systems to use when fulfilling the requirements. 
	
	
	
### User Stories:
#### User
- (U-01) As a User I want to be able to register so that I can post new discussions aswell as comments

- (U-02) As a User I want to be able to log in so that I can use all the features on the site

- (U-03) As a User I want to be able to write discussions so people can comment and vote

- (U-04) As a User I want to be able to see discussions so that I can see what the discussion is about

- (U-05) As a User I want to be able to click on discussions so that I can see what they are about

- (U-06) As a User I want to be able to click on comments so that I can read them

- (U-07) As a User I want to be able to comment on posts so that I can participate in discussions

- (U-08) As a User I want to be able to vote on comments so that I can show my appriciation with karma

- (U-09) As a User I want to be able to vote on discussions so that I can show my interest with karma

- (U-10) As a User I want to be able to acquire karma points so that I can vote

- (U-11) As a User I want to be able to mark a discussion as spam so that meaningless discussions can be removed

- (U-12) As a User I want to be able to mark a comment as spam so that irrelevant comments can be removed

- (U-13) As a User I want an interface so that I can interact with the site

#### Admin
- (A-01) As an Admin I want a running server so that I can build an application

- (A-02) As an Admin I want a running database so that I can save data

- (A-03) As an Admin I want discussions to be stored in a database, so that I don't risk losing it

- (A-04) As an Admin I want comments to be stored in a database, so that I don't risk losing it

- (A-05) As an Admin I want an HTTP API for simulation so that I can test the application

- (A-06) As an Admin I want API access so that external programs can do statistics on the application

- (A-07) As an Admin I want the site deployed at all times on a server, so that users can access it

- (A-08) As a system I want a buffer for content when the site is down, so that I don't lose stuff



## Tasks

### Register User
	1. Entity Mapping (Id, Name, Type, etc...)

### Discussion
	1. Discussion Entity 
	2. Discussion Comments - as a field
	3. Upvote or Downvote feature - as a field(requires login) 
	4. Mark as spam feature

### UI
	1. Commenting on discussion (requires login) 
	2. Overview (Frontpage) 
	3. Log in Page
	4. Discussion View w. Comments
	5. Sequrity?

### Database 
	1. Setup Database (Probably SQL) 
	2. Table for Discussions
	3. Table for Users.
	4. Database Connection, Accesser (Java)

### Additional
	1. API for testing purpose (Specified more / Divided Into Additional Tasks)
	2. Buffer system to hold data, while the system isn't responding
	3. Deploy on Server. 




