package org.au.jwhelan.wowreports.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="factions")
public class Faction {

	private org.au.jwhelan.wowreports.model.Faction name;

	@Id
	@Column(name="name")
	@Enumerated(EnumType.STRING)
	public org.au.jwhelan.wowreports.model.Faction getName() {
		return name;
	}

	public void setName(org.au.jwhelan.wowreports.model.Faction name) {
		this.name = name;
	}
}
