package cu.edu.cujae.touristpacks.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class UrlRewriteConfigurationProvider extends HttpConfigurationProvider {

        @Override
        public Configuration getConfiguration(ServletContext context) {
                return ConfigurationBuilder.begin()

                                .addRule(Join.path("/hotels").to("/pages/crud/hotel/hotel-list.jsf"))
                                .addRule(Join.path("/hotel-chains").to("/pages/crud/hotel_chain/hotelChain-list.jsf"))
                                .addRule(Join.path("/hotel-modalities")
                                                .to("/pages/crud/hotel_modality/hotelModality-list.jsf"))
                                .addRule(Join.path("/seasons").to("/pages/crud/season/season-list.jsf"))
                                .addRule(Join.path("/provinces").to("/pages/crud/province/province-list.jsf"))
                                .addRule(Join.path("/alimentary-plans")
                                                .to("/pages/crud/alimentary_plan/alimentaryPlan-list.jsf"))
                                .addRule(Join.path("/contracts").to("/pages/crud/contract/contract-list.jsf"))
                                .addRule(Join.path("/diary-activities")
                                                .to("/pages/crud/diary_activity/diaryActivity-list.jsf"))
                                .addRule(Join.path("/hotel-contracts")
                                                .to("/pages/crud/hotel_contract/hotelContract-list.jsf"))
                                .addRule(Join.path("/other-service-contracts")
                                                .to("/pages/crud/other_service_contract/otherServiceContract-list.jsf"))
                                .addRule(Join.path("/room-plan-seasons")
                                                .to("/pages/crud/room_plan_season/roomPlanSeason-list.jsf"))
                                .addRule(Join.path("/service-types")
                                                .to("/pages/crud/service_type/serviceType-list.jsf"))
                                .addRule(Join.path("/room-types").to("/pages/crud/room_type/roomType-list.jsf"))
                                .addRule(Join.path("/tourist-packs")
                                                .to("/pages/crud/tourist_pack/touristPack-list.jsf"))
                                .addRule(Join.path("/providers").to("/pages/crud/provider/provider-list.jsf"))
                                .addRule(Join.path("/transport-modalities")
                                                .to("/pages/crud/transport_modality/transportModality-list.jsf"))
                                .addRule(Join.path("/tm-kms").to("/pages/crud/tm_km/tmKm-list.jsf"))
                                .addRule(Join.path("/tm-hour-kms").to("/pages/crud/tm_hour_km/tmHourKm-list.jsf"))
                                .addRule(Join.path("/tm-travels").to("/pages/crud/tm_travel/tmTravel-list.jsf"))
                                .addRule(Join.path("/travels").to("/pages/crud/travel/travel-list.jsf"))
                                .addRule(Join.path("/transport-contracts")
                                                .to("/pages/crud/transport_contract/transportContract-list.jsf"))
                                .addRule(Join.path("/transport-services")
                                                .to("/pages/crud/transport_service/transportService-list.jsf"))
                                .addRule(Join.path("/vehicles").to("/pages/crud/vehicle/vehicle-list.jsf"))
                                .addRule(Join.path("/security-users").to("/pages/security/user/user-list.jsf"))
                                .addRule(Join.path("/security-roles").to("/pages/security/role/role-list.jsf"))
                                .addRule(Join.path("/welcome").to("/pages/welcome/welcome.jsf"));

                // ej using params
                // .addRule(Join.path("/shop/{category}").to("/faces/viewCategory.jsf"));
        }

        @Override
        public int priority() {
                return 0;
        }

}
