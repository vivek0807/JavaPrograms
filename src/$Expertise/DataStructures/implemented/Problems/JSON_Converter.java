package $Expertise.DataStructures.implemented.Problems;

public class JSON_Converter {

    public static void main(String[] args) {
        String inputFilePath="EntityAvroModel(eventType=updateExternalId, entityInFocus=lineitem, source=gamIntegration, updateProps=null, txnId=660e64a90807c30428fc8a32, order=null, creative=null, advertiser=null, lineitem=LineItemMap(id=65fbca3c91134138ecd0f850, name=Sojag PDP LineItem, description=, externalId=, externalStatus=null, startTime=2024-03-21T04:00:00Z, endTime=2024-10-01T03:59:59.999Z, orderId=65fa8a3392e176796492a0f8, externalOrderId=, creativeSizes=null, advertiserId=65fa89883406a570bf60aa27, packageId=65fa8a3392e176796492a0f9, advertisementType=SPONSORED, pricing=null, targeting=null, type=null, priorityValue=null, delivery=null, pacingType=EVEN, productIds=[5013972591, 1001304844, 5013578267, 1002654382, 5013972691, 1001304716, 1001304812, 1001380866, 1001295726, 1002654388, 5013972685, 1001463848, 1001304620, 1001295786, 5013578233, 1001304700, 5013578269, 5013578263, 1001304796, 5013972665, 5013578273, 5013578247, 1001304684, 1001304604, 5013972647, 1001304588, 1001304876, 5013975055], status=RESERVED_AND_READY, creativeStatus=null, createTime=2024-04-04T08:28:25.584460Z, updateTime=2024-04-04T08:28:25.584453Z, createdBy=80086451, updatedBy=80086451, allowOverBooking=null, sponsoredLineItemData=SponsoredLineItemData(placementType=Carousel, placement=PDP_DEDICATED_CAROUSEL, dailyCap=0.0, totalCap=0.0, dailyThreshold=0.0, totalThreshold=0.0, walletId=65fa89e692e176796492a0f6, allowHPCarousel=null, sponsoredDayParting=SponsoredDayParting(fromTime=00:00:00.000+0000, toTime=23:59:59.999+0000)), channelBasedCpc=[ChannelBasedCpc(id=aa39b039-2ee0-468d-ad74-a317dac827de, specifiers=[5/Outdoors/Patio Furniture/Gazebos, Pergolas & Canopies/Gazebos & Accessories/Gazebos], specifiersPath=null, productIds=[5013972591, 5013578263, 5013972691, 1001295786, 1002654382, 5013578267, 1001380866, 1001304812, 1001304844, 1001295726, 1002654388, 5013578273, 1001304796, 5013578233, 1001463848, 1001304684, 1001304716, 1001304620, 1001304700, 5013972665, 5013972685, 5013578269, 5013972647, 5013578247, 1001304876, 1001304588, 1001304604, 5013975055], channelCPCs=[ChannelCPC(channels=[DESKTOP], cpc=0.9), ChannelCPC(channels=[MOW], cpc=0.9), ChannelCPC(channels=[IPHONE], cpc=0.9), ChannelCPC(channels=[ANDROID], cpc=0.9)], placement=PDP_DEDICATED_CAROUSEL, hpCarouselEnabled=false, errorDetails=null)], appliedLabelsLineItem=null, productCategoryMap=null, advertiserTimezone=null, attributionPath=null, partnerData=null, trafficDistributionCategory=null, preview=null), adUnitsMap=null, placementMap=null, lineItemCreativeAssociation=null, customTargetingKeysMap=null, customTargetingValuesMap=null)";

        inputFilePath=inputFilePath.replaceAll("\\s","");
        inputFilePath=inputFilePath.replaceAll("=","\":\"");
        inputFilePath=inputFilePath.replaceAll(",","\",\"");
        inputFilePath=inputFilePath.replace("(","{");
        inputFilePath=inputFilePath.replace(")","}");
        inputFilePath=inputFilePath.replaceAll("\"null\"","null");

        //inputFilePath=inputFilePath.replace("=","\",\"");
        System.out.println(inputFilePath);

    }
}
