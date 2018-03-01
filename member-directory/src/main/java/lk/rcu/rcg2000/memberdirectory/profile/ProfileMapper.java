package lk.rcu.rcg2000.memberdirectory.profile;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile requestToProfile(ProfileRequest request);

    ProfileResponse ProfileToResponse(Profile profile);
}
