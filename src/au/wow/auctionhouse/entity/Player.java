package au.wow.auctionhouse.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import au.wow.auctionhouse.model.Faction;


/**
 * The persistent class for the players database table.
 * 
 */
@Entity
@Table(name="players")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="player_id")
	private String playerId;

	@Enumerated(EnumType.STRING)
	private Faction faction;

	private String realm;

    public Player() {
    }

	public String getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public Faction getFaction() {
		return this.faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

}