// 
// Decompiled by Procyon v0.5.36
// 

package se.michaelthelin.spotify.requests.data.search.simplified;

import se.michaelthelin.spotify.requests.AbstractRequest;
import se.michaelthelin.spotify.requests.IRequest;
import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import java.io.IOException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

@JsonDeserialize(builder = Builder.class)
public class SearchAlbumsRequest extends AbstractDataRequest<Paging<AlbumSimplified>>
{
    private SearchAlbumsRequest(final Builder builder) {
        super(builder);
    }
    
    @Override
    public Paging<AlbumSimplified> execute() throws IOException, SpotifyWebApiException, ParseException {
        return new AlbumSimplified.JsonUtil().createModelObjectPaging(this.getJson(), "albums");
    }
    
    public static final class Builder extends AbstractDataPagingRequest.Builder<AlbumSimplified, Builder>
    {
        public Builder(final String accessToken) {
            super(accessToken);
        }
        
        public Builder q(final String q) {
            assert q != null;
            assert !q.equals("");
            return ((AbstractRequest.Builder<T, Builder>)this).setQueryParameter("q", q);
        }
        
        public Builder market(final CountryCode market) {
            assert market != null;
            return ((AbstractRequest.Builder<T, Builder>)this).setQueryParameter("market", market);
        }
        
        @Override
        public Builder limit(final Integer limit) {
            assert limit != null;
            assert 1 <= limit && limit <= 50;
            return ((AbstractRequest.Builder<T, Builder>)this).setQueryParameter("limit", limit);
        }
        
        @Override
        public Builder offset(final Integer offset) {
            assert offset != null;
            assert 0 <= offset && offset <= 100000;
            return ((AbstractRequest.Builder<T, Builder>)this).setQueryParameter("offset", offset);
        }
        
        public Builder includeExternal(final String includeExternal) {
            assert includeExternal != null;
            assert includeExternal.matches("audio");
            return ((AbstractRequest.Builder<T, Builder>)this).setQueryParameter("include_external", includeExternal);
        }
        
        @Override
        public SearchAlbumsRequest build() {
            this.setPath("/v1/search");
            ((AbstractRequest.Builder<Object, AbstractRequest.Builder>)this).setQueryParameter("type", "album");
            return new SearchAlbumsRequest(this, null);
        }
        
        @Override
        protected Builder self() {
            return this;
        }
    }
}
