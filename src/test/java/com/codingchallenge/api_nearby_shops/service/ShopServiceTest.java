package com.codingchallenge.api_nearby_shops.service;


import com.codingchallenge.api_nearby_shops.model.Reaction;
import com.codingchallenge.api_nearby_shops.model.ReactionType;
import com.codingchallenge.api_nearby_shops.model.Shop;
import com.codingchallenge.api_nearby_shops.repository.ShopRepository;
import com.codingchallenge.api_nearby_shops.service.dto.ShopDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.geo.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {

    @Mock
    ShopRepository shopRepository;

    @Mock
    UserService userService;

    @InjectMocks
    ShopService shopService;

    @Test
    public void should_return_nearby_shops_to_default_location_of_authenticated_user(){
        List<GeoResult<Shop>> shopGeoResultList = Arrays.asList(
                new GeoResult<Shop>(Fixture.shops().get(0), new Distance(0.021, Metrics.KILOMETERS)),
                new GeoResult<Shop>(Fixture.shops().get(1), new Distance(0.100, Metrics.KILOMETERS))
        );
        ArgumentCaptor<LocalDateTime> localDateTimeArgumentCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        ArgumentCaptor<Point> pointArgumentCaptor = ArgumentCaptor.forClass(Point.class);
        ArgumentCaptor<Distance> distanceArgumentCaptor = ArgumentCaptor.forClass(Distance.class);
        ArgumentCaptor<String> userIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        GeoResults<Shop> shopGeoResults = new GeoResults<>(shopGeoResultList);
        when(userService.getAuthenticatedUser()).thenReturn(Fixture.user());
        when(shopRepository.getShopsByLocationNearAndShopIsNotFavorite(any(Point.class), any(Distance.class), anyString(), any(LocalDateTime.class)))
        .thenReturn(shopGeoResults);

        List<ShopDTO> shopDTOList = shopService.getNearbyShopsByDefaultUserLocation();
        verify(userService).getAuthenticatedUser();
        verify(shopRepository).getShopsByLocationNearAndShopIsNotFavorite(
                pointArgumentCaptor.capture(),
                distanceArgumentCaptor.capture(),
                userIdArgumentCaptor.capture(),
                localDateTimeArgumentCaptor.capture()
        );

        assertThat(pointArgumentCaptor.getValue().getX()).isEqualTo(Fixture.user().getLocation().getX());
        assertThat(pointArgumentCaptor.getValue().getY()).isEqualTo(Fixture.user().getLocation().getY());
        assertThat(userIdArgumentCaptor.getValue()).isEqualTo(Fixture.user().getId());

        shopGeoResults.getContent().stream().forEach(shopGeoResult -> {
            Shop shop = shopGeoResult.getContent();
            assertThat(shopDTOList.stream().filter(shopDTO -> shopDTO.getId().equals(shop.getId())).count()).isEqualTo(1);
        });
    }

    @Test
    public void should_return_nearby_shops_to_a_custom_location(){
        List<GeoResult<Shop>> shopGeoResultList = Arrays.asList(
                new GeoResult<Shop>(Fixture.shops().get(0), new Distance(0.021, Metrics.KILOMETERS)),
                new GeoResult<Shop>(Fixture.shops().get(1), new Distance(0.100, Metrics.KILOMETERS))
        );
        ArgumentCaptor<LocalDateTime> localDateTimeArgumentCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        ArgumentCaptor<Point> pointArgumentCaptor = ArgumentCaptor.forClass(Point.class);
        ArgumentCaptor<Distance> distanceArgumentCaptor = ArgumentCaptor.forClass(Distance.class);
        ArgumentCaptor<String> userIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        GeoResults<Shop> shopGeoResults = new GeoResults<>(shopGeoResultList);
        when(userService.getAuthenticatedUser()).thenReturn(Fixture.user());
        when(shopRepository.getShopsByLocationNearAndShopIsNotFavorite(any(Point.class), any(Distance.class), anyString(), any(LocalDateTime.class)))
                .thenReturn(shopGeoResults);

        List<ShopDTO> shopDTOList = shopService.getNearbyShopsByLocation(Fixture.location());
        verify(userService).getAuthenticatedUser();
        verify(shopRepository).getShopsByLocationNearAndShopIsNotFavorite(
                pointArgumentCaptor.capture(),
                distanceArgumentCaptor.capture(),
                userIdArgumentCaptor.capture(),
                localDateTimeArgumentCaptor.capture()
        );

        assertThat(pointArgumentCaptor.getValue().getX()).isEqualTo(Fixture.location().getX());
        assertThat(pointArgumentCaptor.getValue().getY()).isEqualTo(Fixture.location().getY());
        assertThat(userIdArgumentCaptor.getValue()).isEqualTo(Fixture.user().getId());

        shopGeoResults.getContent().stream().forEach(shopGeoResult -> {
            Shop shop = shopGeoResult.getContent();
            assertThat(shopDTOList.stream().filter(shopDTO -> shopDTO.getId().equals(shop.getId())).count()).isEqualTo(1);
        });
    }

    @Test
    public void should_return_favorites_shops(){
        List<Shop> favoritesShops = Fixture.shops();
        when(userService.getAuthenticatedUser()).thenReturn(Fixture.user());
        when(shopRepository.getShopsByUserIdAndReactionType(Fixture.user().getId(), ReactionType.LIKE)).thenReturn(favoritesShops);

        List<ShopDTO> shopDTOList = shopService.getFavoritesShops();

        verify(userService).getAuthenticatedUser();
        verify(shopRepository).getShopsByUserIdAndReactionType(Fixture.user().getId(), ReactionType.LIKE);
        favoritesShops.stream().forEach(shop -> {
            assertThat(shopDTOList.stream().filter(shopDTO -> shopDTO.getId().equals(shop.getId())).count()).isEqualTo(1);
        });
    }

    @Test
    public void should_add_or_update_user_reaction_about_shop(){
        Shop shop = Fixture.shops().get(0);
        ArgumentCaptor<Reaction> reactionArgumentCaptor = ArgumentCaptor.forClass(Reaction.class);
        ArgumentCaptor<String> shopIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(userService.getAuthenticatedUser()).thenReturn(Fixture.user());
        shopService.addOrUpdateUserReactionAboutShop(ReactionType.LIKE, shop.getId());
        verify(userService).getAuthenticatedUser();
        verify(shopRepository).addOrUpdateUserReactionUsingShopId(reactionArgumentCaptor.capture(), shopIdArgumentCaptor.capture());
        assertThat(reactionArgumentCaptor.getValue().getReactionType()).isEqualTo(ReactionType.LIKE);
        assertThat(reactionArgumentCaptor.getValue().getUserId()).isEqualTo(Fixture.user().getId());
        assertThat(shopIdArgumentCaptor.getValue()).isEqualTo(shop.getId());
    }

    @Test
    public void should_remove_shop_from_favorites_shops(){
        when(userService.getAuthenticatedUser()).thenReturn(Fixture.user());
        Shop shop = Fixture.shops().get(0);
        when(userService.getAuthenticatedUser()).thenReturn(Fixture.user());
        shopService.removeUserReactionAboutShop(shop.getId(), ReactionType.LIKE);
        verify(userService).getAuthenticatedUser();
        verify(shopRepository).removeUserReactionUsingShopIdAndUserId(Fixture.user().getId(), shop.getId(), ReactionType.LIKE);
    }
}
