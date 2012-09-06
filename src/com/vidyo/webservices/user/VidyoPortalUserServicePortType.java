
package com.vidyo.webservices.user;

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
@WebService(name = "VidyoPortalUserServicePortType", targetNamespace = "http://portal.vidyo.com/user/v1_1")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface VidyoPortalUserServicePortType {


    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.LogInResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "logIn")
    @WebResult(name = "LogInResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public LogInResponse logIn(
        @WebParam(name = "LogInRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        LogInRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.LinkEndpointResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws AccessRestrictedFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "linkEndpoint")
    @WebResult(name = "LinkEndpointResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public LinkEndpointResponse linkEndpoint(
        @WebParam(name = "LinkEndpointRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        LinkEndpointRequest parameter)
        throws AccessRestrictedFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.LogOutResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "logOut")
    @WebResult(name = "LogOutResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public LogOutResponse logOut(
        @WebParam(name = "LogOutRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        LogOutRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.MyEndpointStatusResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "myEndpointStatus")
    @WebResult(name = "MyEndpointStatusResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public MyEndpointStatusResponse myEndpointStatus(
        @WebParam(name = "MyEndpointStatusRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        MyEndpointStatusRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.SearchMyContactsResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "searchMyContacts")
    @WebResult(name = "SearchMyContactsResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public SearchMyContactsResponse searchMyContacts(
        @WebParam(name = "SearchMyContactsRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        SearchMyContactsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.AddToMyContactsResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "addToMyContacts")
    @WebResult(name = "AddToMyContactsResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public AddToMyContactsResponse addToMyContacts(
        @WebParam(name = "AddToMyContactsRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        AddToMyContactsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.RemoveFromMyContactsResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "removeFromMyContacts")
    @WebResult(name = "RemoveFromMyContactsResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public RemoveFromMyContactsResponse removeFromMyContacts(
        @WebParam(name = "RemoveFromMyContactsRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        RemoveFromMyContactsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.SearchResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "search")
    @WebResult(name = "SearchResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public SearchResponse search(
        @WebParam(name = "SearchRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        SearchRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.SearchByEntityIDResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "searchByEntityID")
    @WebResult(name = "SearchByEntityIDResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public SearchByEntityIDResponse searchByEntityID(
        @WebParam(name = "SearchByEntityIDRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        SearchByEntityIDRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.SearchByEmailResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "searchByEmail")
    @WebResult(name = "SearchByEmailResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public SearchByEmailResponse searchByEmail(
        @WebParam(name = "SearchByEmailRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        SearchByEmailRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.GetEntityByEntityIDResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "getEntityByEntityID")
    @WebResult(name = "GetEntityByEntityIDResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public GetEntityByEntityIDResponse getEntityByEntityID(
        @WebParam(name = "GetEntityByEntityIDRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        GetEntityByEntityIDRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.InviteToConferenceResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "inviteToConference")
    @WebResult(name = "InviteToConferenceResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public InviteToConferenceResponse inviteToConference(
        @WebParam(name = "InviteToConferenceRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        InviteToConferenceRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.JoinConferenceResponse
     * @throws NotLicensedFault_Exception
     * @throws ConferenceLockedFault_Exception
     * @throws WrongPinFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "joinConference")
    @WebResult(name = "JoinConferenceResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public JoinConferenceResponse joinConference(
        @WebParam(name = "JoinConferenceRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        JoinConferenceRequest parameter)
        throws ConferenceLockedFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception, WrongPinFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.JoinIPCConferenceResponse
     * @throws NotLicensedFault_Exception
     * @throws ConferenceLockedFault_Exception
     * @throws WrongPinFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "joinIPCConference")
    @WebResult(name = "JoinIPCConferenceResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public JoinIPCConferenceResponse joinIPCConference(
        @WebParam(name = "JoinIPCConferenceRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        JoinIPCConferenceRequest parameter)
        throws ConferenceLockedFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception, WrongPinFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.DirectCallResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "directCall")
    @WebResult(name = "DirectCallResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public DirectCallResponse directCall(
        @WebParam(name = "DirectCallRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        DirectCallRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.GetParticipantsResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "getParticipants")
    @WebResult(name = "GetParticipantsResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public GetParticipantsResponse getParticipants(
        @WebParam(name = "GetParticipantsRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        GetParticipantsRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.LeaveConferenceResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "leaveConference")
    @WebResult(name = "LeaveConferenceResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public LeaveConferenceResponse leaveConference(
        @WebParam(name = "LeaveConferenceRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        LeaveConferenceRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.MuteAudioResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "muteAudio")
    @WebResult(name = "MuteAudioResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public MuteAudioResponse muteAudio(
        @WebParam(name = "MuteAudioRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        MuteAudioRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.UnmuteAudioResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "unmuteAudio")
    @WebResult(name = "UnmuteAudioResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public UnmuteAudioResponse unmuteAudio(
        @WebParam(name = "UnmuteAudioRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        UnmuteAudioRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.StartVideoResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "startVideo")
    @WebResult(name = "StartVideoResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public StartVideoResponse startVideo(
        @WebParam(name = "StartVideoRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        StartVideoRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.StopVideoResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "stopVideo")
    @WebResult(name = "StopVideoResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public StopVideoResponse stopVideo(
        @WebParam(name = "StopVideoRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        StopVideoRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.MyAccountResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "myAccount")
    @WebResult(name = "MyAccountResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public MyAccountResponse myAccount(
        @WebParam(name = "MyAccountRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        MyAccountRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.CreateRoomURLResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "createRoomURL")
    @WebResult(name = "CreateRoomURLResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public CreateRoomURLResponse createRoomURL(
        @WebParam(name = "CreateRoomURLRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        CreateRoomURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.RemoveRoomURLResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "removeRoomURL")
    @WebResult(name = "RemoveRoomURLResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public RemoveRoomURLResponse removeRoomURL(
        @WebParam(name = "RemoveRoomURLRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        RemoveRoomURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.CreateRoomPINResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "createRoomPIN")
    @WebResult(name = "CreateRoomPINResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public CreateRoomPINResponse createRoomPIN(
        @WebParam(name = "CreateRoomPINRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        CreateRoomPINRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.RemoveRoomPINResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "removeRoomPIN")
    @WebResult(name = "RemoveRoomPINResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public RemoveRoomPINResponse removeRoomPIN(
        @WebParam(name = "RemoveRoomPINRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        RemoveRoomPINRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.CreateRoomResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "createRoom")
    @WebResult(name = "CreateRoomResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public CreateRoomResponse createRoom(
        @WebParam(name = "CreateRoomRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        CreateRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.DeleteRoomResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "deleteRoom")
    @WebResult(name = "DeleteRoomResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public DeleteRoomResponse deleteRoom(
        @WebParam(name = "DeleteRoomRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        DeleteRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.UpdatePasswordResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "updatePassword")
    @WebResult(name = "UpdatePasswordResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public UpdatePasswordResponse updatePassword(
        @WebParam(name = "UpdatePasswordRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        UpdatePasswordRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.UpdateLanguageResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "updateLanguage")
    @WebResult(name = "UpdateLanguageResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public UpdateLanguageResponse updateLanguage(
        @WebParam(name = "UpdateLanguageRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        UpdateLanguageRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.LockRoomResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "lockRoom")
    @WebResult(name = "LockRoomResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public LockRoomResponse lockRoom(
        @WebParam(name = "LockRoomRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        LockRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.UnlockRoomResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "unlockRoom")
    @WebResult(name = "UnlockRoomResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public UnlockRoomResponse unlockRoom(
        @WebParam(name = "UnlockRoomRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        UnlockRoomRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.SetMemberModeResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "setMemberMode")
    @WebResult(name = "SetMemberModeResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public SetMemberModeResponse setMemberMode(
        @WebParam(name = "SetMemberModeRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        SetMemberModeRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.GetRecordingProfilesResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "getRecordingProfiles")
    @WebResult(name = "GetRecordingProfilesResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public GetRecordingProfilesResponse getRecordingProfiles(
        @WebParam(name = "GetRecordingProfilesRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        GetRecordingProfilesRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.StartRecordingResponse
     * @throws NotLicensedFault_Exception
     * @throws ResourceNotAvailableFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "startRecording")
    @WebResult(name = "StartRecordingResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public StartRecordingResponse startRecording(
        @WebParam(name = "StartRecordingRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        StartRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, ResourceNotAvailableFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.GetPortalVersionResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "getPortalVersion")
    @WebResult(name = "GetPortalVersionResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public GetPortalVersionResponse getPortalVersion(
        @WebParam(name = "GetPortalVersionRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        GetPortalVersionRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.PauseRecordingResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "pauseRecording")
    @WebResult(name = "PauseRecordingResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public PauseRecordingResponse pauseRecording(
        @WebParam(name = "PauseRecordingRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        PauseRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.ResumeRecordingResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "resumeRecording")
    @WebResult(name = "ResumeRecordingResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public ResumeRecordingResponse resumeRecording(
        @WebParam(name = "ResumeRecordingRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        ResumeRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.StopRecordingResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "stopRecording")
    @WebResult(name = "StopRecordingResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public StopRecordingResponse stopRecording(
        @WebParam(name = "StopRecordingRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        StopRecordingRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.CreateWebcastURLResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "createWebcastURL")
    @WebResult(name = "CreateWebcastURLResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public CreateWebcastURLResponse createWebcastURL(
        @WebParam(name = "CreateWebcastURLRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        CreateWebcastURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.CreateWebcastPinResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "createWebcastPin")
    @WebResult(name = "CreateWebcastPinResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public CreateWebcastPinResponse createWebcastPin(
        @WebParam(name = "CreateWebcastPinRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        CreateWebcastPinRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.RemoveWebcastURLResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "removeWebcastURL")
    @WebResult(name = "RemoveWebcastURLResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public RemoveWebcastURLResponse removeWebcastURL(
        @WebParam(name = "RemoveWebcastURLRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        RemoveWebcastURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.RemoveWebcastPinResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "removeWebcastPin")
    @WebResult(name = "RemoveWebcastPinResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public RemoveWebcastPinResponse removeWebcastPin(
        @WebParam(name = "RemoveWebcastPinRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        RemoveWebcastPinRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.GetWebcastURLResponse
     * @throws NotLicensedFault_Exception
     * @throws SeatLicenseExpiredFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "getWebcastURL")
    @WebResult(name = "GetWebcastURLResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public GetWebcastURLResponse getWebcastURL(
        @WebParam(name = "GetWebcastURLRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        GetWebcastURLRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception
    ;

    /**
     * 
     * @param parameter
     * @return
     *     returns com.vidyo.webservices.user.GetUserNameResponse
     * @throws NotLicensedFault_Exception
     * @throws GeneralFault_Exception
     * @throws InvalidArgumentFault_Exception
     */
    @WebMethod(action = "getUserName")
    @WebResult(name = "GetUserNameResponse", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
    public GetUserNameResponse getUserName(
        @WebParam(name = "GetUserNameRequest", targetNamespace = "http://portal.vidyo.com/user/v1_1", partName = "parameter")
        GetUserNameRequest parameter)
        throws GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception
    ;

}