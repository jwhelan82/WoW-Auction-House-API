package au.wow.auctionhouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="factions")
public class Faction {

	private au.wow.auctionhouse.model.Faction name;

	@Id
	@Column(name="name")
	@Enumerated(EnumType.STRING)
	public au.wow.auctionhouse.model.Faction getName() {
		return name;
	}

	public void setName(au.wow.auctionhouse.model.Faction name) {
		this.name = name;
	}
}
