package io;

public class Codes {

	public static String EventSubString = "EVENT";

	public static String TransportPriceUpdate = "EVENT.PRICE.UPDATE.TRANSPORT";
	public static String MailCreation = "EVENT.MAIL.CREATION";
	public static String CustomerPriceUpdate = "EVENT.PRICE.UPDATE.CUSTOMER";
	public static String TransportDiscontinue = "EVENT.TRANSPORT.DISCONTINUE";

	public static String ClientGetRoutesMailDelivery = "CLIENT.GET.ROUTES.MAIL.DELIVERY";

	public static String ServerMailDeliveryRoutes = "SERVER.MAIL.DELIVERY.ROUTES";
	public static String ServerCheapestRoute = "SERVER.CHEAPEST.ROUTE";
	public static String ServerFastestRoute = "SERVER.FASTEST.ROUTE";
	
	public static String ServerFailedMailDeliveryRoutes = "FAIL.MAIL.DELIVERY.ROUTES";

	public static String ConfirmationUpdateRoute = "CONFIRMATION.UPDATE.ROUTE";
	public static String ConfirmationMadeRoute = "CONFIRMATION.MADE.ROUTE";

	public static String ConfirmationMadeCustomerRoute = "CONFIRMATION.MADE.CUSTOMER.ROUTE";
	public static String ConfirmationUpdateCustomerRoute = "CONFIRMATION.UPDATE.CUSTOMER.ROUTE";

	public static String ConfirmationMailDelivery = "CONFIRMATION.MAIL.DELIVERY";
	public static String ConfirmationCustomerPriceUpdate = "CONFIRMATION.CUSTOMER.PRICE.UPDATE";

	public static String DiscontinueRouteValid = "DISCONTINUE.ROUTE.VALID";
	public static String DiscontinueRouteInvalid = "DISCONTINUE.ROUTE.INVALID";

	public static String loginValid = "LOGIN.VALID";
	public static String loginInvalid = "LOGIN.INVALID";

	public static String loginDetails = "LOGIN.DETAILS";

	public static String ServerRouteList = "SERVER.ROUTE.LIST";
	public static String ServerCompanyList = "SERVER.COMPANY.LIST";

	public static String ServerNewRoute = "SERVER.NEW.ROUTE";
	public static String ServerNewCompany = "SERVER.NEW.COMPANY";

	public static String BroadcastSingle = "BROADCAST.SINGLE";
	public static String BroadcastAll = "BROADCAST.ALL";
	
	public static String ServerAccountingFigures = "ACCOUNTING.FIGURES";
	public static String MailDeliveryStats = "MAIL.STATS";
	public static String CriticalRoutes = "CRITICAL.ROUTES";
	
	public static String EventLog = "GET.EVENT.LOG";
	public static String LogMailDelivery = "LOG.MAIL.DELIVERY";
	public static String LogCustomerUpdate = "LOG.CUSTOMER.U";
	public static String LogTransportUpdate = "LOG.TRANSPORT.UPDATE";
	public static String LogTransportDiscontinue = "LOG.TRANSPORT.DISCONTINUE";

	
	public static String GUIMailDelivery = "Mail Delivery";
	public static String GUICustomerPriceUpdate = "Customer Price Update";
	public static String GUIDiscontinueRoute = "Discontinue Route";
	public static String GUIUpdatedRoute = "Updated Route through transport update";
	public static String GUIUpdatedCustomerRoute = "Updated Route through customer update";
	public static String GUIConfirmationMadeRoute = "Made new Route through transport update";
	public static String GUIConfirmationMadeCustomerRoute = "Made new Route through customer update";
	public static String GUIServerFailedMailDeliveryRoutes = "No routes were found";
	public static String GUIDiscontinueRouteInvalid = "No route was found to discontinue";
	public static String GUIInvalidLogin = "Invalid Login";
	
	public static String GUISameRouteMailDelivery = "Select Different Origin/Destination in Mail Delivery";
	public static String GUISameRouteCustomerPrice = "Select Different Origin/Destination in Customer Price Update";
	public static String GUISameRouteTransportCost = "Select Different Origin/Destination in Transport Price Update";
	public static String GUISameRouteTransportDisc = "Select Different Origin/Destination in Transport Discontinue";
	
	public static class Priorities{
		public static String InternationalAir = "International Air";
		public static String InternationalStandard = "International Standard";
		public static String DomesticAir = "Domestic Air";
		public static String DomesticStandard = "Domestic Standard";
	}

}
