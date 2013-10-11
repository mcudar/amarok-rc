package de.mgd.amarok.remote.service.internal.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mgd.amarok.remote.model.Album;
import de.mgd.amarok.remote.model.Artist;
import de.mgd.amarok.remote.model.Track;
import de.mgd.amarok.remote.service.internal.HelperService;

public class HelperServiceImpl implements HelperService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public Track buildTrackFromJSONObject(final JSONObject track) {
		Track.Builder builder = new Track.Builder();
		
		builder.setAlbum(track.optString("album"))
		.setArtist(track.optString("artist"))
		.setTitle(track.optString("title"))
		.setNumber(track.optInt("number"))
		.setId(track.optInt("id"))
		.setIndexInPlaylist(track.optInt("index"))
		.setLength(track.optLong("length"));
		
		return builder.build();
	}
	
	@Override
	public Artist buildArtistFromJSONObject(final JSONObject artist) {
		Artist.Builder builder = new Artist.Builder();
		
		builder.setId(artist.optInt("id")).setName(artist.optString("name"));
		
		return builder.build();
	}

	@Override
	public Album buildAlbumFromJSONObject(final JSONObject album) {
		Album.Builder builder = new Album.Builder();
		
		builder.setId(album.optInt("id"))
		.setName(album.optString("name"))
		.setImageId(album.optInt("image"))
		.setTrackCount(album.optInt("tracks"));
		
		return builder.build();
	}
	
}
