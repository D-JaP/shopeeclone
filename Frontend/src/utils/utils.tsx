// change link 's hostname change to your hostname
export const changeLinkHostname = (to_do_url: string) => {
    let url = new URL(to_do_url);
    
    url.hostname = window.location.hostname;
    url.port = window.location.port;

    return url.toString();

}

export function convertToSlug(str) {
  // Replace spaces with hyphens
  str = str.replace(/\s+/g, '-');
  
  // Convert to lowercase
  str = str.toLowerCase();
  
  // Remove special characters
  str = str.replace(/[^\w\-]+/g, '');
  
  return str;
}

export function chunkArray(array: any[], size: number) {
  let result = [];
  for (let i = 0; i < array.length; i += size) {
    let chunk = array.slice(i, i + size);
    result.push(chunk);
  }
  return result;
}

export function extractIdFromSlug(slug:string) {
  // split slug with the "-" at the end of the string, take the number after it
  console.log(slug);
  const id = slug.split("-").pop();
  return id;
}

export function convertStringFromSlugExcludeEndId(slug:string){
  const fullString = slug.replace(/-/g, ' ')
  const stringWithCapitals = fullString.replace(/\b\w/g, char => char.toUpperCase());
  const stringWithCapitalsArray = stringWithCapitals.split(' ')
  console.log(stringWithCapitalsArray[stringWithCapitalsArray.length -1]);
  
  if (isStringNumber(stringWithCapitalsArray[stringWithCapitalsArray.length -1])){
    // concencate arr except end position
    const result = stringWithCapitalsArray.slice(0,-1).join(' ')
    return result
  } 
  return stringWithCapitals
}


function isStringNumber(str: string): boolean {
  // Use the isNaN function to check if the string can be converted to a number
  return !isNaN(parseFloat(str)) && isFinite(parseFloat(str));
}