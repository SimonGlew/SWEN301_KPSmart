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
	


	public static class Priorities{
		public static String InternationalAir = "International Air";
		public static String InternationalStandard = "International Standard";
		public static String DomesticAir = "Domestic Air";
		public static String DomesticStandard = "Domestic Standard";
	}

}
