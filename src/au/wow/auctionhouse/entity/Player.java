package au.wow.auctionhouse.entity;

import java.io.Serializable;
import javax.persistence.*;


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

	private String faction;

	private String realm;

    public Player() {
    }

	public String getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getFaction() {
		return this.faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

}