package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import org.superbiz.moviefun.moviesapi.MovieInfo;
//import org.superbiz.moviefun.moviesapi.MovieInfo;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {
    private String albumsUrl;
    private RestOperations restOperations;

//    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
//    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class);
//        entityManager.persist(album);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForEntity(albumsUrl + "/" + id, AlbumInfo.class).getBody();
//        return entityManager.find(Album.class, id);
    }

    public List<AlbumInfo> getAlbums() {
        ParameterizedTypeReference<List<AlbumInfo>> albumListType =
                new ParameterizedTypeReference<List<AlbumInfo>>() {};
        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
        //        CriteriaQuery<Album> cq = entityManager.getCriteriaBuilder().createQuery(Album.class);
//        cq.select(cq.from(Album.class));
//        return entityManager.createQuery(cq).getResultList();
    }

    public void deleteAlbum(AlbumInfo album) {

//        entityManager.remove(album);
    }

    public void updateAlbum(AlbumInfo album) {

//        entityManager.merge(album);
    }


}
