
package com.vidyo.webservices.admin;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "VidyoPortalAdminServicePortType", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface VidyoPortalAdminServicePortType {


    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetMembersResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getMembers")
    @WebResult(name = "GetMembersResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetMembersResponse getMembers(
        @WebParam(name = "GetMembersRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetMembersRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetMemberResponse
     * @throws GeneralFault_Exception
     * @throws MemberNotFoundFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getMember")
    @WebResult(name = "GetMemberResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetMemberResponse getMember(
        @WebParam(name = "GetMemberRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetMemberRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, MemberNotFoundFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.AddMemberResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     * @throws MemberAlreadyExistsFault_Exception
     */
    @WebMethod(action = "addMember")
    @WebResult(name = "AddMemberResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public AddMemberResponse addMember(
        @WebParam(name = "AddMemberRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        AddMemberRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, MemberAlreadyExistsFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.UpdateMemberResponse
     * @throws GeneralFault_Exception
     * @throws MemberNotFoundFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "updateMember")
    @WebResult(name = "UpdateMemberResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public UpdateMemberResponse updateMember(
        @WebParam(name = "UpdateMemberRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        UpdateMemberRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, MemberNotFoundFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.DeleteMemberResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws MemberNotFoundFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "deleteMember")
    @WebResult(name = "DeleteMemberResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public DeleteMemberResponse deleteMember(
        @WebParam(name = "DeleteMemberRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        DeleteMemberRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, MemberNotFoundFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetRoomsResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getRooms")
    @WebResult(name = "GetRoomsResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetRoomsResponse getRooms(
        @WebParam(name = "GetRoomsRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetRoomsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetRoomResponse
     * @throws GeneralFault_Exception
     * @throws RoomNotFoundFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getRoom")
    @WebResult(name = "GetRoomResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetRoomResponse getRoom(
        @WebParam(name = "GetRoomRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, RoomNotFoundFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.AddRoomResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws RoomAlreadyExistsFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "addRoom")
    @WebResult(name = "AddRoomResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public AddRoomResponse addRoom(
        @WebParam(name = "AddRoomRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        AddRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, RoomAlreadyExistsFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.UpdateRoomResponse
     * @throws GeneralFault_Exception
     * @throws RoomNotFoundFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws RoomAlreadyExistsFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "updateRoom")
    @WebResult(name = "UpdateRoomResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public UpdateRoomResponse updateRoom(
        @WebParam(name = "UpdateRoomRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        UpdateRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, RoomAlreadyExistsFault_Exception, RoomNotFoundFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.DeleteRoomResponse
     * @throws GeneralFault_Exception
     * @throws RoomNotFoundFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "deleteRoom")
    @WebResult(name = "DeleteRoomResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public DeleteRoomResponse deleteRoom(
        @WebParam(name = "DeleteRoomRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        DeleteRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, RoomNotFoundFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetGroupsResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getGroups")
    @WebResult(name = "GetGroupsResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetGroupsResponse getGroups(
        @WebParam(name = "GetGroupsRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetGroupsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetGroupResponse
     * @throws GroupNotFoundFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getGroup")
    @WebResult(name = "GetGroupResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetGroupResponse getGroup(
        @WebParam(name = "GetGroupRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetGroupRequest parameter)
        throws GeneralFault_Exception, GroupNotFoundFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.AddGroupResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     * @throws GroupAlreadyExistsFault_Exception
     */
    @WebMethod(action = "addGroup")
    @WebResult(name = "AddGroupResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public AddGroupResponse addGroup(
        @WebParam(name = "AddGroupRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        AddGroupRequest parameter)
        throws GeneralFault_Exception, GroupAlreadyExistsFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.UpdateGroupResponse
     * @throws GroupNotFoundFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "updateGroup")
    @WebResult(name = "UpdateGroupResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public UpdateGroupResponse updateGroup(
        @WebParam(name = "UpdateGroupRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        UpdateGroupRequest parameter)
        throws GeneralFault_Exception, GroupNotFoundFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.DeleteGroupResponse
     * @throws GroupNotFoundFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "deleteGroup")
    @WebResult(name = "DeleteGroupResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public DeleteGroupResponse deleteGroup(
        @WebParam(name = "DeleteGroupRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        DeleteGroupRequest parameter)
        throws GeneralFault_Exception, GroupNotFoundFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetParticipantsResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getParticipants")
    @WebResult(name = "GetParticipantsResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetParticipantsResponse getParticipants(
        @WebParam(name = "GetParticipantsRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetParticipantsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.InviteToConferenceResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "inviteToConference")
    @WebResult(name = "InviteToConferenceResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public InviteToConferenceResponse inviteToConference(
        @WebParam(name = "InviteToConferenceRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        InviteToConferenceRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.LeaveConferenceResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "leaveConference")
    @WebResult(name = "LeaveConferenceResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public LeaveConferenceResponse leaveConference(
        @WebParam(name = "LeaveConferenceRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        LeaveConferenceRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.MuteAudioResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "muteAudio")
    @WebResult(name = "MuteAudioResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public MuteAudioResponse muteAudio(
        @WebParam(name = "MuteAudioRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        MuteAudioRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.UnmuteAudioResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "unmuteAudio")
    @WebResult(name = "UnmuteAudioResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public UnmuteAudioResponse unmuteAudio(
        @WebParam(name = "UnmuteAudioRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        UnmuteAudioRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.StartVideoResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "startVideo")
    @WebResult(name = "StartVideoResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public StartVideoResponse startVideo(
        @WebParam(name = "StartVideoRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        StartVideoRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.StopVideoResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "stopVideo")
    @WebResult(name = "StopVideoResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public StopVideoResponse stopVideo(
        @WebParam(name = "StopVideoRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        StopVideoRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.CreateRoomURLResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "createRoomURL")
    @WebResult(name = "CreateRoomURLResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public CreateRoomURLResponse createRoomURL(
        @WebParam(name = "CreateRoomURLRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        CreateRoomURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.RemoveRoomURLResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "removeRoomURL")
    @WebResult(name = "RemoveRoomURLResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public RemoveRoomURLResponse removeRoomURL(
        @WebParam(name = "RemoveRoomURLRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        RemoveRoomURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.CreateRoomPINResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "createRoomPIN")
    @WebResult(name = "CreateRoomPINResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public CreateRoomPINResponse createRoomPIN(
        @WebParam(name = "CreateRoomPINRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        CreateRoomPINRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.RemoveRoomPINResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "removeRoomPIN")
    @WebResult(name = "RemoveRoomPINResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public RemoveRoomPINResponse removeRoomPIN(
        @WebParam(name = "RemoveRoomPINRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        RemoveRoomPINRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetLicenseDataResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getLicenseData")
    @WebResult(name = "GetLicenseDataResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetLicenseDataResponse getLicenseData(
        @WebParam(name = "GetLicenseDataRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetLicenseDataRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetPortalVersionResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getPortalVersion")
    @WebResult(name = "GetPortalVersionResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetPortalVersionResponse getPortalVersion(
        @WebParam(name = "GetPortalVersionRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        Object parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetRecordingProfilesResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getRecordingProfiles")
    @WebResult(name = "GetRecordingProfilesResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetRecordingProfilesResponse getRecordingProfiles(
        @WebParam(name = "GetRecordingProfilesRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        Object parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.StartRecordingResponse
     * @throws GeneralFault_Exception
     * @throws ResourceNotAvailableFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "startRecording")
    @WebResult(name = "StartRecordingResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public StartRecordingResponse startRecording(
        @WebParam(name = "StartRecordingRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        StartRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, ResourceNotAvailableFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.PauseRecordingResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "pauseRecording")
    @WebResult(name = "PauseRecordingResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public PauseRecordingResponse pauseRecording(
        @WebParam(name = "PauseRecordingRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        PauseRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.ResumeRecordingResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "resumeRecording")
    @WebResult(name = "ResumeRecordingResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public ResumeRecordingResponse resumeRecording(
        @WebParam(name = "ResumeRecordingRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        ResumeRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.StopRecordingResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "stopRecording")
    @WebResult(name = "StopRecordingResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public StopRecordingResponse stopRecording(
        @WebParam(name = "StopRecordingRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        StopRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetLocationTagsResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getLocationTags")
    @WebResult(name = "GetLocationTagsResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetLocationTagsResponse getLocationTags(
        @WebParam(name = "GetLocationTagsRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetLocationTagsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.GetWebcastURLResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "getWebcastURL")
    @WebResult(name = "GetWebcastURLResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public GetWebcastURLResponse getWebcastURL(
        @WebParam(name = "GetWebcastURLRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        GetWebcastURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.CreateWebcastURLResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "createWebcastURL")
    @WebResult(name = "CreateWebcastURLResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public CreateWebcastURLResponse createWebcastURL(
        @WebParam(name = "CreateWebcastURLRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        CreateWebcastURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.CreateWebcastPinResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "createWebcastPin")
    @WebResult(name = "CreateWebcastPinResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public CreateWebcastPinResponse createWebcastPin(
        @WebParam(name = "CreateWebcastPinRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        CreateWebcastPinRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.RemoveWebcastURLResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "removeWebcastURL")
    @WebResult(name = "RemoveWebcastURLResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public RemoveWebcastURLResponse removeWebcastURL(
        @WebParam(name = "RemoveWebcastURLRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        RemoveWebcastURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.admin.RemoveWebcastPinResponse
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     * @throws NotLicensedFault_Exception
     */
    @WebMethod(action = "removeWebcastPin")
    @WebResult(name = "RemoveWebcastPinResponse", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
    public RemoveWebcastPinResponse removeWebcastPin(
        @WebParam(name = "RemoveWebcastPinRequest", targetNamespace = "http://portal.vidyo.com/admin/v1_1", partName = "parameter")
        RemoveWebcastPinRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

}
