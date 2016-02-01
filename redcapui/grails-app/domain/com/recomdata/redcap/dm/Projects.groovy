package com.recomdata.redcap.dm

class Projects {
	Long id
	String projectID
	Servers server
	String name
	java.sql.Timestamp lastProcessed
	String status
	boolean selected
	String token
	
	static mapping = {
		table 'projects'
		version false

		columns {
			id column:'rec_id'
			projectID column:'project_id'
		}
	}

    static constraints = {
		lastProcessed(nullable:true)
		status(nullable:true)
		selected(nullable:true)
		token(nullable:true)
    }
}
