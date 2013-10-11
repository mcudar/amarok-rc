package de.mgd.amarok.remote.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.mgd.amarok.remote.model.Track;
import de.mgd.amarok.remote.service.PlaylistService;

public class PlaylistServiceImpl extends AbstractRemoteService implements PlaylistService {

	@Override
	public void clearPlaylist() {
		remoteService.getResponseAsString(host, port, "cmdPlaylistClear/");
	}

	@Override
	public List<Track> listAllTracks() {
		final String allTracks = remoteService.getResponseAsString(host, port, "playlistAllTracks/");
		List<Track> tracks = new ArrayList<Track>();
		if(allTracks == null) {
			return Collections.emptyList();
		}
		
		try {
			JSONArray trackList = new JSONArray(allTracks);
			for(int i=0; i<trackList.length(); i++) {
				JSONObject track = trackList.getJSONObject(i);
				Track t = helperService.buildTrackFromJSONObject(track);
				if(t != null) {
					tracks.add(t);
				}
			}
		} catch (JSONException e) {
			log.error("Unable to deserialize playlist", e);
			return Collections.emptyList();
		}
		
		return tracks;
	}

	@Override
	public void playTrackAtIndex(final int index) {
		if(index < 0) {
			throw new IllegalArgumentException("Encountered negative index for playlist-position!");
		}
		// TODO: check for total playlist-length??
		remoteService.getResponseAsString(host, port, "cmdPlayByIndex/"+index);
	}
	
	@Override
	public byte[] coverAtIndex(final int index) {
		if(index < 0) {
			throw new IllegalArgumentException("Encountered negative index for playlist-position!");
		}
		
		return remoteService.getResponseAsByteArray(host, port, "playlistCoverAt/"+index);
	}

}
