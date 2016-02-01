package com.recomdata.redcap.dm

class Servers {
	Long id
	String name
	String baseURL
	String userId

	static hasMany = [projects: Projects]
	static mapping = {
		table 'servers'
		version false

		columns {
			id column:'ID'
			name column:'name'
			baseURL column:'base_url'
		}
	}

    static constraints = {
		userId nullable:true
    }
}
