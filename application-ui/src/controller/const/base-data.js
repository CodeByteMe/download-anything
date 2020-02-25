const isProduction = process.env.NODE_ENV === 'production';
export let BASE_URL = isProduction ? "" : "http://localhost:10086";
